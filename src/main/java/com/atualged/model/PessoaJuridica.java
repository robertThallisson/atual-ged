package com.atualged.model;

import java.io.Serializable;

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
public class PessoaJuridica implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 2, max = 180)
	private String razaoSocial;
	@NotNull
	@Size(min = 2, max = 180)
	private String nomeFantasia;
	@NotNull
	private Long cnpj;
	@NotNull
	@Size(min = 2, max = 200)
	private String logradouro;
	@NotNull
	@Size(min = 2, max = 100)
	private String bairro;
	@NotNull
	private Long numero;
	@NotNull
	@Size(min = 2, max = 100)
	private String complemento;
	@NotNull
	private Boolean ativo;
	@OneToOne
	private Cidade cidade;
	

	
	
	private byte[] foto;
	
	/***********Get_Set*********/
	
}
