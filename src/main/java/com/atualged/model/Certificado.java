package com.atualged.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Entity
@Table(name = "certificado")
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
	
	private byte arquivo[]; 
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	public long getId() {
		return id;
	}

	public String getIpo() {
		return ipo;
	}

	public String getEmitido() {
		return emitido;
	}

	public LocalDate getValidadeIni() {
		return validadeIni;
	}

	public LocalDate getValidadeFim() {
		return validadeFim;
	}

	public String getSerial() {
		return serial;
	}

	public int getPin() {
		return pin;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIpo(String ipo) {
		this.ipo = ipo;
	}

	public void setEmitido(String emitido) {
		this.emitido = emitido;
	}

	public void setValidadeIni(LocalDate validadeIni) {
		this.validadeIni = validadeIni;
	}

	public void setValidadeFim(LocalDate validadeFim) {
		this.validadeFim = validadeFim;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

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
