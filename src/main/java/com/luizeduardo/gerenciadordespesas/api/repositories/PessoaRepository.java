package com.luizeduardo.gerenciadordespesas.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luizeduardo.gerenciadordespesas.api.model.Pessoa;
import com.luizeduardo.gerenciadordespesas.api.repositories.pessoa.PessoaRepositoryQuery;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery{


}
