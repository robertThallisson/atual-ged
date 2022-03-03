package com.atualged.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	private LocalDate dataBloqueio;
	@NotNull
	private Boolean ativo;
	@OneToOne
	private Pessoa pessoa;
	
	@OneToOne
    private PerfilUsuario perfilUsuario;
	


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
