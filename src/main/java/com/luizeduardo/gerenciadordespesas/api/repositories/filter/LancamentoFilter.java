package com.luizeduardo.gerenciadordespesas.api.repositories.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoFilter {

	private String descricao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDeVencimentoDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDeVencimentoAte;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataDeVencimentoDe() {
		return dataDeVencimentoDe;
	}

	public void setDataDeVencimentoDe(LocalDate dataDeVencimentoDe) {
		this.dataDeVencimentoDe = dataDeVencimentoDe;
	}

	public LocalDate getDataDeVencimentoAte() {
		return dataDeVencimentoAte;
	}

	public void setDataDeVencimentoAte(LocalDate dataDeVencimentoAte) {
		this.dataDeVencimentoAte = dataDeVencimentoAte;
	}

}
