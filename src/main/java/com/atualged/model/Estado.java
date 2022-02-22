package com.atualged.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min = 2, max = 2)
	private String uf;
	@NotNull
	@Size(min = 2, max = 80)
	private String nome;	
	@NotNull
	private Boolean ativo;
	@Transient
	private List<Cidade> cidade;

	
	
}
