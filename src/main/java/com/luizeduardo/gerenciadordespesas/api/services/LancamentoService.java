package com.luizeduardo.gerenciadordespesas.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizeduardo.gerenciadordespesas.api.model.Categoria;
import com.luizeduardo.gerenciadordespesas.api.model.Lancamento;
import com.luizeduardo.gerenciadordespesas.api.model.Pessoa;
import com.luizeduardo.gerenciadordespesas.api.repositories.CategoriaRepository;
import com.luizeduardo.gerenciadordespesas.api.repositories.LancamentoRepository;
import com.luizeduardo.gerenciadordespesas.api.repositories.PessoaRepository;
import com.luizeduardo.gerenciadordespesas.api.services.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	

	public Lancamento cadastrarLancamento(Lancamento lancamento) {

		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getId());

		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}

		return lancamentoRepository.save(lancamento);
	}

	public Lancamento atualizarLancamento(Long id, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoPorId(id);

		if (lancamento.getPessoa().getId()!=null) {
			Pessoa pessoa = validarPessoa(lancamento.getPessoa());
			lancamentoSalvo.setPessoa(pessoa);
		}
		
		if(lancamento.getCategoria().getId()!=null) {
			Categoria categoria = validarCategoria(lancamento.getCategoria());
			lancamentoSalvo.setCategoria(categoria);
		}
		
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "id");

		 lancamentoSalvo = lancamentoRepository.save(lancamentoSalvo);
		 return lancamentoSalvo;
	}

	private Categoria validarCategoria(Categoria categoria) {
		
		Categoria categoriaSalva = null;
		
		if(categoria.getId() !=null) {
			categoria = categoriaRepository.findOne(categoria.getId());
		}
		
		if(categoria == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return categoriaSalva;
	}

	private Pessoa validarPessoa(Pessoa pessoaLancamento) {

		Pessoa pessoa = null;

		if (pessoaLancamento.getId() != null) {
			pessoa = pessoaRepository.findOne(pessoaLancamento.getId());
		}

		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return pessoa;
	}

	private Lancamento buscarLancamentoPorId(Long id) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);

		if (lancamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return lancamentoSalvo;
	}
}
