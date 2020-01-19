package com.luizeduardo.gerenciadordespesas.api.repositories.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.luizeduardo.gerenciadordespesas.api.model.Lancamento;
import com.luizeduardo.gerenciadordespesas.api.model.Lancamento_;
import com.luizeduardo.gerenciadordespesas.api.repositories.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		// criar as restrições
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {

			predicates.add(builder.like(builder.lower(root.get(Lancamento_.DESCRICAO)),
					"%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
		}

		if (lancamentoFilter.getDataDeVencimentoDe() != null) {

			predicates.add(builder.greaterThanOrEqualTo(root.get(Lancamento_.DATA_VENCIMENTO),
					lancamentoFilter.getDataDeVencimentoDe()));
		}

		if (lancamentoFilter.getDataDeVencimentoAte() != null) {

			predicates.add(builder.lessThanOrEqualTo(root.get(Lancamento_.DATA_VENCIMENTO),
					lancamentoFilter.getDataDeVencimentoAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
