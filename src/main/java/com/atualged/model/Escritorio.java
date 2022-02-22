package com.atualged.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "escritorio")
public class Escritorio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private Boolean ativo;
	@ManyToOne
	@JoinColumn(name = "pessoa_juridica_id")
	private PessoaJuridica pessoa_juridica;
	@OneToMany()
	@JoinColumn(name = "escritorio_id",updatable = false)
	private List<Contador> contador;

	/***********Get_Set*********/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public PessoaJuridica getPessoa_juridica() {
		return pessoa_juridica;
	}

	public void setPessoa_juridica(PessoaJuridica pessoa_juridica) {
		this.pessoa_juridica = pessoa_juridica;
	}
	
	public List<Contador> getContador() {
		return contador;
	}

	public void setContador(List<Contador> contador) {
		this.contador = contador;
	}
	/***********equa_hash*********/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

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

