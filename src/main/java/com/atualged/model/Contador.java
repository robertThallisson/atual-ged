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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "contador")
public class Contador {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min = 2, max = 200)
	private String nome;
	@NotNull
	private int rg;
	@NotNull
	@Size(min = 2, max = 14)
	private String cpf;
	@NotNull
	@Size(min = 2, max = 15)
	private String crc;
	@NotNull
	private Boolean ativo;
	@ManyToOne
	@JoinColumn(name = "escritorio_id")
	@JsonBackReference
	private Escritorio escritorio;
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public int getRg() {
		return rg;
	}
	public String getCpf() {
		return cpf;
	}
	public String getCrc() {
		return crc;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public Escritorio getEscritorio() {
		return escritorio;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setRg(int rg) {
		this.rg = rg;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setCrc(String crc) {
		this.crc = crc;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public void setEscritorio(Escritorio escritorio) {
		this.escritorio = escritorio;
	}
	
	/***********Get_Set*********/
	
	
	

}
