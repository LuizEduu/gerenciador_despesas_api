package com.luizeduardo.gerenciadordespesas.api.repositories.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.luizeduardo.gerenciadordespesas.api.model.Pessoa;
import com.luizeduardo.gerenciadordespesas.api.model.Pessoa_;


public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Pessoa> filtrar(String nome, Pageable pageable) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteriaQuery = builder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteriaQuery.from(Pessoa.class);

		Predicate[] predicates = criarRestricoes(nome, builder, root);
		criteriaQuery.where(predicates);

		TypedQuery<Pessoa> query = entityManager.createQuery(criteriaQuery);

		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, totalDeRegistros(nome));
	}

	
	private Predicate[] criarRestricoes(String nome, CriteriaBuilder builder, Root<Pessoa> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(nome)) {
			predicates.add(builder.like(builder.lower(root.get(Pessoa_.NOME)),
					"%" + nome.toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalDeRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalDeRegistrosPorPagina);
	}
	
	
	private Long totalDeRegistros(String nome) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Pessoa> root = query.from(Pessoa.class);
		
		Predicate[] predicates = criarRestricoes(nome, builder, root);
		query.where(predicates);
		
		query.select(builder.count(root));
		
		return entityManager.createQuery(query).getSingleResult();
		
	}

}
