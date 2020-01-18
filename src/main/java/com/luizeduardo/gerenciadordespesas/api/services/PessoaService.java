package com.luizeduardo.gerenciadordespesas.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizeduardo.gerenciadordespesas.api.model.Pessoa;
import com.luizeduardo.gerenciadordespesas.api.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa AtualizarPessoaPorId(Pessoa pessoa, Long id) {
		Pessoa pessoaSalva = buscaPorId(id);

		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");

		return pessoaRepository.save(pessoaSalva);
	}

	public void atualizarAtivo(Long id, Boolean ativo) {
		Pessoa pessoaSalva = buscaPorId(id);
		
		pessoaSalva.setAtivo(ativo);
		
		pessoaRepository.save(pessoaSalva);
	}

	private Pessoa buscaPorId(Long id) {
		Pessoa pessoaSalva = pessoaRepository.findOne(id);

		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}

}
