package com.atualged.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contador {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 2, max = 200)
	private String nome;
	@NotNull
	private Long rg;
	@NotNull
	@Size(min = 2, max = 14)
	private String cpf;
	@NotNull
	@Size(min = 2, max = 15)
	private String crc;
	@NotNull
	private Boolean ativo;
	
	@OneToOne
	private Escritorio escritorio;

	
	/***********Get_Set*********/
	
	
	

}
