package com.atualged.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Entity
@Table(name = "usuario")
public class Usuario  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min = 2, max = 180)
	private String login;
	@NotNull
	@Size(min = 2, max = 180)
	@Email
	@Column(unique = true)
	private String email;
	
	@NotBlank
	private String senha;
	
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate Dt_bloqueio;
	@NotNull
	private Boolean ativo;
	@ManyToOne
	@JoinColumn(name = "pessoa_juridica_id")
	private PessoaJuridica pessoa_juridica;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "permissao_usuario", joinColumns = { @JoinColumn(updatable = false,name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(updatable = false,name = "permissao_id") })
	private List<Permissao> permissoes;
	
	/***********Get_Set*********/
	
	
	public String getEmail() {
		return email;
	}
	public long getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public LocalDate getDt_bloqueio() {
		return Dt_bloqueio;
	}
	public void setDt_bloqueio(LocalDate dt_bloqueio) {
		Dt_bloqueio = dt_bloqueio;
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
	public List<Permissao> getPermissoes() {
		return permissoes;
	}
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
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
		Usuario other = (Usuario) obj;
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
