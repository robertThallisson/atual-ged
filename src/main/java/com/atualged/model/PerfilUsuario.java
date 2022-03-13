package com.atualged.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class PerfilUsuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@OneToOne
	private Escritorio escritorio;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "permissao_perfil_usuario", joinColumns = {
			@JoinColumn(updatable = false,name = "perfil_usuario_id") }, inverseJoinColumns = {
	@JoinColumn(updatable = false,name = "permissao_id") })
    private List<Permissao> permissoes;	
}
