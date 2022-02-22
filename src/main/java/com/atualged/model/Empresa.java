package com.atualged.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "empresa")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private Boolean ativo;
	@ManyToOne
	@JoinColumn(name = "pessoa_juridica_id")
	private PessoaJuridica pessoaJuridica;
	@JsonManagedReference
	@OneToMany()
	@JoinColumn(updatable = false,name = "empresa_id")
	private List<Certificado> certificado;
	@OneToMany()
	@JoinColumn(updatable = false,name = "empresa_id")
	@JsonManagedReference
	private List<Socios> socios;
	@OneToMany()
	@JoinColumn(updatable = false,name = "empresa_id")
	@JsonManagedReference
	private List<Fornecedor> fornecedor;
	@OneToMany()
	@JoinColumn(updatable = false,name = "empresa_id")
	@JsonManagedReference
	private List<Notas> notas;
	public long getId() {
		return id;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}
	public List<Certificado> getCertificado() {
		return certificado;
	}
	public List<Socios> getSocios() {
		return socios;
	}
	public List<Fornecedor> getFornecedor() {
		return fornecedor;
	}
	public List<Notas> getNotas() {
		return notas;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}
	public void setCertificado(List<Certificado> certificado) {
		this.certificado = certificado;
	}
	public void setSocios(List<Socios> socios) {
		this.socios = socios;
	}
	public void setFornecedor(List<Fornecedor> fornecedor) {
		this.fornecedor = fornecedor;
	}
	public void setNotas(List<Notas> notas) {
		this.notas = notas;
	}
	
	/***********Get_Set*********/
	
	
	
}
