package com.luizeduardo.gerenciadordespesas.api.repositories.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luizeduardo.gerenciadordespesas.api.model.Pessoa;

public interface PessoaRepositoryQuery {
	
	public Page<Pessoa> filtrar(String nome, Pageable pageable);

}
