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
public class Socio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 2, max = 180)
	private String nome;
	@NotNull
	private Long cpf;
	@NotNull
	private Long rg;
	@NotNull
	@Size(min = 2, max = 80)
	private String orgEsp;
	@JsonDeserialize(using = LocalDateDeserializer.class) @JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dtEmissao;
	@NotNull
	private Boolean ativo;
	@OneToOne
	private Empresa empresa;
	

}
