package com.atualged.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "estado")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min = 2, max = 2)
	private String uf;
	@NotNull
	@Size(min = 2, max = 80)
	private String nome;	
	@NotNull
	private Boolean ativo;
	@OneToMany()
	@JoinColumn(name = "estado_id", updatable = true)
	@JsonBackReference
	private List<Cidade> cidade;
	public long getId() {
		return id;
	}
	public String getUf() {
		return uf;
	}
	public String getNome() {
		return nome;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public List<Cidade> getCidade() {
		return cidade;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public void setCidade(List<Cidade> cidade) {
		this.cidade = cidade;
	}
	
	/***********Get_Set*********/
	
	
}
