package com.atualged.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atualged.business.PerfilUsuarioBS;
import com.atualged.model.PerfilUsuario;

@Service
public class PerfilUsuarioCtrl {
	
	@Autowired
	private PerfilUsuarioBS pb;
	
	public void salvar(PerfilUsuario perfilUsuario) {
		pb.salvar(perfilUsuario);
	}
	
	public List<PerfilUsuario> pesquisar(String nome) {
		return pb.pesquisar(nome);
	}
	
	public List<PerfilUsuario> pesquisarUltimosRegistros() {
		return pb.pesquisarUltimosRegistros();
	}
	
	public void deletar(PerfilUsuario perfilUsuario) {
		pb.deletar(perfilUsuario);
	}

}
