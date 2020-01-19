package com.luizeduardo.gerenciadordespesas.api.repositories.lancamento;

import java.util.List;

import com.luizeduardo.gerenciadordespesas.api.model.Lancamento;
import com.luizeduardo.gerenciadordespesas.api.repositories.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
