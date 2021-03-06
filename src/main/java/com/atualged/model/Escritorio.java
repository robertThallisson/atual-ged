package com.atualged.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Escritorio implements Serializable{

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
	
	@Transient
	//@JsonIgnore
	private List<Contador> contador;
	
	
	private Float valorBase;
	private Float valorPorCliente;
	private Float baseCliente;


	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)  
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAdessao;
	
	/***********equa_hash*********/
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Escritorio other = (Escritorio) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

	/*************************/
	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.ativo;
	}


	
	

}

