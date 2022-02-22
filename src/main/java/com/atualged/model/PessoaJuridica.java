package com.atualged.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Entity
public class PessoaJuridica {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min = 2, max = 180)
	private String razao_soc;
	@NotNull
	@Size(min = 2, max = 180)
	private String nome_fan;
	@NotNull
	private int cnpj;
	@NotNull
	@Size(min = 2, max = 200)
	private String logradouro;
	@NotNull
	@Size(min = 2, max = 100)
	private String bairro;
	@NotNull
	private int numero;
	@NotNull
	@Size(min = 2, max = 100)
	private String complemento;
	@NotNull
	private Boolean ativo;
	@OneToOne
	private Cidade cidade;
	
	@JsonDeserialize(using = LocalDateDeserializer.class) @JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dtAdesao;
	
	/***********Get_Set*********/
	
}
