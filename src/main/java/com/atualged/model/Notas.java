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
@Table(name = "notas")
public class Notas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private int numero;
	@NotNull
	private int serie;
	@NotNull
	private int tipo;
	@NotNull
	private float valorTotal;
	@JsonDeserialize(using = LocalDateDeserializer.class) @JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dtEmissao;
	@JsonDeserialize(using = LocalDateDeserializer.class) @JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dtSaida;
	@NotNull
	private int tipoOperacao;
	@NotNull
	private int finalidadeOperacao;
	@NotNull
	private int cnpjForn;
	@NotNull
	private int cnpjEmp;
	@NotNull
	@Size(min = 2, max = 280)
	private String xml;
	@NotNull
	private Boolean ativo;
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	@JsonBackReference
	private Fornecedor fornecedor;
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	@JsonBackReference
	private Empresa empresa;
	public long getId() {
		return id;
	}
	public int getNumero() {
		return numero;
	}
	public int getSerie() {
		return serie;
	}
	public int getTipo() {
		return tipo;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public LocalDate getDtEmissao() {
		return dtEmissao;
	}
	public LocalDate getDtSaida() {
		return dtSaida;
	}
	public int getTipoOperacao() {
		return tipoOperacao;
	}
	public int getFinalidadeOperacao() {
		return finalidadeOperacao;
	}
	public int getCnpjForn() {
		return cnpjForn;
	}
	public int getCnpjEmp() {
		return cnpjEmp;
	}
	public String getXml() {
		return xml;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setSerie(int serie) {
		this.serie = serie;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public void setDtEmissao(LocalDate dtEmissao) {
		this.dtEmissao = dtEmissao;
	}
	public void setDtSaida(LocalDate dtSaida) {
		this.dtSaida = dtSaida;
	}
	public void setTipoOperacao(int tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	public void setFinalidadeOperacao(int finalidadeOperacao) {
		this.finalidadeOperacao = finalidadeOperacao;
	}
	public void setCnpjForn(int cnpjForn) {
		this.cnpjForn = cnpjForn;
	}
	public void setCnpjEmp(int cnpjEmp) {
		this.cnpjEmp = cnpjEmp;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	

}
