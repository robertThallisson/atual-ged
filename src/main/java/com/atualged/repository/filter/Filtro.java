package com.atualged.repository.filter;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filtro {
	
	private String sql;
	private String orderBy;
	private String groupBy;
	private String limite;
	private boolean nativo;
	private  ArrayList<Pesquisa> pesquisas;

	public ArrayList<Pesquisa> getPesquisas() {
		return pesquisas;
	}

	public void setPesquisas(ArrayList<Pesquisa> pesquisas) {
		this.pesquisas = pesquisas;
	}
}
