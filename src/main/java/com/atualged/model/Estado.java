package com.atualged.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Estado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 2, max = 2)
	private String uf;
	@NotNull
	@Size(min = 2, max = 80)
	private String nome;	
	@NotNull
	private Boolean ativo;
	
	
	
	private Integer ibge   ;
	@OneToOne
	private Pais  pais   ;
	private String  ddd    ;
	  
	  
	@Transient
	private List<Cidade> cidade;

	
	
}
