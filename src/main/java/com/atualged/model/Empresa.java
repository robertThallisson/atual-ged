package com.atualged.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private Boolean ativo;
	@ManyToOne
	@JoinColumn(name = "pessoa_juridica_id")
	private PessoaJuridica pessoaJuridica;
	@JsonManagedReference
	@OneToMany()
	@JoinColumn(updatable = false,name = "empresa_id")
	private List<Certificado> certificado;
	@OneToMany()
	@JoinColumn(updatable = false,name = "empresa_id")
	@JsonManagedReference
	private List<Socio> socios;
	@OneToMany()
	@JoinColumn(updatable = false,name = "empresa_id")
	@JsonManagedReference
	private List<Fornecedor> fornecedor;
	@OneToMany()
	@JoinColumn(updatable = false,name = "empresa_id")
	@JsonManagedReference
	private List<Notas> notas;
	
	private String uf;
	
	
}
