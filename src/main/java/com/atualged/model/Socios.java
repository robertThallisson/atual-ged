package com.atualged.model;

import java.time.LocalDate;

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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Entity
@Table(name = "socios")
public class Socios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min = 2, max = 180)
	private String nome;
	@NotNull
	private int cpf;
	@NotNull
	private int rg;
	@NotNull
	@Size(min = 2, max = 80)
	private String orgEsp;
	@JsonDeserialize(using = LocalDateDeserializer.class) @JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dtEmissao;
	@NotNull
	private Boolean ativo;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(updatable = false,name = "empresa_id", nullable = false)
	private Empresa empresa;
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public int getCpf() {
		return cpf;
	}
	public int getRg() {
		return rg;
	}
	public String getOrgEsp() {
		return orgEsp;
	}
	public LocalDate getDtEmissao() {
		return dtEmissao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public void setRg(int rg) {
		this.rg = rg;
	}
	public void setOrgEsp(String orgEsp) {
		this.orgEsp = orgEsp;
	}
	public void setDtEmissao(LocalDate dtEmissao) {
		this.dtEmissao = dtEmissao;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	

}
