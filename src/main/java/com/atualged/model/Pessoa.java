package com.atualged.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pessoa  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 2, max = 100)
	@NotBlank(message = "campo obrigatório")
	@Column
	private String nome;	
	@CPF
	@Size(max = 18)
	@NotBlank(message = "campo obrigatório")
	@Column
	private String cpf;
	@Size(max = 20)
	@Column
	private String rg;
	@Size(max = 15)
	@NotBlank(message = "campo obrigatório")
	@Column
	private String telefone1;
	@Size(max = 15)
	@Column
	private String telefone2;
	@Size(max = 80)
	@Column
	private String logradouro;
	@Column
	private String numero;
	@Size(max = 80)
	@Column
	private String complemento;
	@Size(max = 80)
	@Column
	private String bairro;
	@Size(max = 80)
	@Column
	private String cidade;
	@Size(max = 20)
	@Column
	private String cep;	
	@Size(max = 2)
	@Column
	private String uf;
	@Size(max = 80)
	@Column
	private String estado;
	@Column
	private byte[] foto;
	@NotNull
	@Column
    private Boolean ativo;
	@OneToOne
	private Empresa empresa;

}
