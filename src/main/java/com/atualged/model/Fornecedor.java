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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	@NotNull
	private Boolean ativo;
	/**
	 * Campo empresa não obrigatorio preenchido 
	 * somente quando fornecedor for tambem cliente
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	@JsonBackReference
	private Empresa empresa;
	@ManyToOne
	@JoinColumn(name = "pessoa_juridica_id")
	@JsonBackReference
	private PessoaJuridica pessoa_juridica;
	@OneToMany()
	@JoinColumn(updatable = false,name = "fornecedor_id")
	@JsonManagedReference
	private List<Notas> notas;
	




}
