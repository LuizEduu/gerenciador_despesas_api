package com.luizeduardo.gerenciadordespesas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.luizeduardo.gerenciadordespesas.api.model.Lancamento;
import com.luizeduardo.gerenciadordespesas.api.repositories.LancamentoRepository;

public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	private Lancamento buscarLancamentoPorId(Long id) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);

		if (lancamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return lancamentoSalvo;
	}
}
