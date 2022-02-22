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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	@NotNull
	private Boolean ativo;
	/**
	 * Campo empresa não obrigatorio preenchido 
	 * somente quando fornecedor for tambem cliente
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	@JsonBackReference
	private Empresa empresa;
	@ManyToOne
	@JoinColumn(name = "pessoa_juridica_id")
	@JsonBackReference
	private PessoaJuridica pessoa_juridica;
	@OneToMany()
	@JoinColumn(updatable = false,name = "fornecedor_id")
	@JsonManagedReference
	private List<Notas> notas;
	
	/***********Get_Set*********/
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public PessoaJuridica getPessoa_juridica() {
		return pessoa_juridica;
	}
	public void setPessoa_juridica(PessoaJuridica pessoa_juridica) {
		this.pessoa_juridica = pessoa_juridica;
	}
	public List<Notas> getNotas() {
		return notas;
	}
	public void setNotas(List<Notas> notas) {
		this.notas = notas;
	}




}
