package com.atualged.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "cidade")
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min = 2, max = 80)
	private String nome;
	@NotNull
	private int cep;
	@ManyToOne
	@JoinColumn(name = "estado_id")
	@JsonManagedReference
	private Estado estado;
	@NotNull
	private Boolean ativo;
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public int getCep() {
		return cep;
	}
	public Estado getEstado() {
		return estado;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	/***********Get_Set*********/
	
	
	}
