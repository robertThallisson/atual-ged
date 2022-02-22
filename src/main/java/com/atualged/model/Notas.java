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
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtEmissao;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
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

	
}
