package com.luizeduardo.gerenciadordespesas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizeduardo.gerenciadordespesas.api.model.Lancamento;
import com.luizeduardo.gerenciadordespesas.api.model.Pessoa;
import com.luizeduardo.gerenciadordespesas.api.repositories.LancamentoRepository;
import com.luizeduardo.gerenciadordespesas.api.repositories.PessoaRepository;
import com.luizeduardo.gerenciadordespesas.api.services.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Lancamento cadastrarLancamento(Lancamento lancamento) {
		
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getId());
		
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}
	
	private Lancamento buscarLancamentoPorId(Long id) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);

		if (lancamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return lancamentoSalvo;
	}
}
