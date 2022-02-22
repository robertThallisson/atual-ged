package com.atualged.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min = 2, max = 180)
	private String razao_soc;
	@NotNull
	@Size(min = 2, max = 180)
	private String nome_fan;
	@NotNull
	private int cnpj;
	@NotNull
	@Size(min = 2, max = 200)
	private String logradouro;
	@NotNull
	@Size(min = 2, max = 100)
	private String bairro;
	@NotNull
	private int numero;
	@NotNull
	@Size(min = 2, max = 100)
	private String complemento;
	@NotNull
	private Boolean ativo;
	@OneToOne
	private Cidade cidade;
	@OneToMany()
	@JoinColumn(name = "pessoa_juridica_id",updatable = false)
	@JsonManagedReference
	private List<Fornecedor> fornecedor;
	@OneToMany()
	@JoinColumn(name = "pessoa_juridica_id",updatable = false)
	@JsonManagedReference
	private List<Escritorio> escritorio;
	@OneToMany()
	@JoinColumn(name = "pessoa_juridica_id",updatable = false)
	@JsonManagedReference
	private List<Empresa> empresa;
	@OneToMany()
	@JoinColumn(name = "pessoa_juridica_id",updatable = false)
	@JsonManagedReference
	private List<Usuario> usuario;
	@JsonDeserialize(using = LocalDateDeserializer.class) @JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dtAdesao;
	public long getId() {
		return id;
	}
	public String getRazao_soc() {
		return razao_soc;
	}
	public String getNome_fan() {
		return nome_fan;
	}
	public int getCnpj() {
		return cnpj;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public int getNumero() {
		return numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public List<Fornecedor> getFornecedor() {
		return fornecedor;
	}
	public List<Escritorio> getEscritorio() {
		return escritorio;
	}
	public List<Empresa> getEmpresa() {
		return empresa;
	}
	public List<Usuario> getUsuario() {
		return usuario;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setRazao_soc(String razao_soc) {
		this.razao_soc = razao_soc;
	}
	public void setNome_fan(String nome_fan) {
		this.nome_fan = nome_fan;
	}
	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public void setFornecedor(List<Fornecedor> fornecedor) {
		this.fornecedor = fornecedor;
	}
	public void setEscritorio(List<Escritorio> escritorio) {
		this.escritorio = escritorio;
	}
	public void setEmpresa(List<Empresa> empresa) {
		this.empresa = empresa;
	}
	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}
	
	
	/***********Get_Set*********/
	
}
