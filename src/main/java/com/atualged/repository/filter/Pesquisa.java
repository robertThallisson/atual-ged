package com.atualged.repository.filter;

import javax.persistence.criteria.JoinType;

public class Pesquisa {
	String nome;
	String descricao;
	String comparacao;
	String valor;
	String tabela;
	

	JoinType tipe;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public JoinType getTipe() {
		return tipe;
	}
	public void setTipe(JoinType tipe) {
		this.tipe = tipe;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getComparacao() {
		return comparacao;
	}
	public void setComparacao(String comparacao) {
		this.comparacao = comparacao;
	}
	public String getValor() {
		return valor;
	}
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
