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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Empresa implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	private Boolean ativo;
	@OneToOne
	private PessoaJuridica pessoaJuridica;
	
	@OneToOne
	private Escritorio escritorio;
	
//	@JsonDeserialize(using = LocalDateDeserializer.class) 
//	@JsonFormat(pattern="dd/MM/yyyy")
//	private LocalDate dataAdessao;
	
	@Transient
	private List<Socio> socios;
	
	@Transient
	private List<Nota> notas;
	
	private String uf;
	

	
}
