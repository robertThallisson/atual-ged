package com.atualged.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Nota implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long numero;
	@NotNull
	private Long serie;
	@NotNull
	private Long tipo;
	@NotNull
	private float valorTotal;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtEmissao;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtSaida;
	@NotNull
	private Long tipoOperacao;
	@NotNull
	private Long finalidadeOperacao;
	@NotNull
	private Long cnpjForn;
	@NotNull
	private Long cnpjEmp;
	@NotNull
	@Size(min = 2, max = 280)
	private String xml;
	@NotNull
	private Boolean ativo;
	@OneToOne
	private Empresa empresa;

	
}
