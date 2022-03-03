package com.atualged.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 2, max = 80)
	private String nome;
	@NotNull
	private Long cep;
	
	private String codigoIbge;
	@JsonIgnore
	@OneToOne
	private Estado estado;
	@NotNull
	private Boolean ativo;
	
	
	
	private String populacao_2010;
	private Float densidade_demo ;
	private String gentilico ;
	private Float area ;

	
	/***********Get_Set*********/
	
	
	}
