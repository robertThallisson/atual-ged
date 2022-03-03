package com.atualged.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	private Boolean ativo;
	@OneToOne
	private PessoaJuridica pessoaJuridica;
	
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataAdessao;
	
	@Transient
	private List<Socio> socios;
	
	@Transient
	private List<Nota> notas;
	
	private String uf;
	
}
