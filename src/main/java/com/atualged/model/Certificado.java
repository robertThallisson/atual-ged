package com.atualged.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Certificado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min = 2, max = 2)
	private String ipo;
	@NotNull
	@Size(min = 2, max = 80)
	private String emitido;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate validadeIni;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate validadeFim;
	@NotNull
	@Size(min = 2, max = 280)
	private String serial;
	@NotNull
	private int pin;
	@NotNull
	private Boolean ativo;
	
	private String senha;
	
	private byte arquivo[]; 
	
	@OneToOne
	private Empresa empresa;



	@Override
	public String toString() {
		return "Certificado [id=" + id + ", ipo=" + ipo + ", emitido=" + emitido + ", validadeIni=" + validadeIni
				+ ", validadeFim=" + validadeFim + ", serial=" + serial + ", pin=" + pin + ", ativo=" + ativo
				+ ", empresa=" + empresa + "]";
	}

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
		Certificado other = (Certificado) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

	/*********** Get_Set *********/

}
