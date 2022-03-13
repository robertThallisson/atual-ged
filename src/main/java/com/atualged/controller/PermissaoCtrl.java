package com.atualged.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atualged.business.PermissaoBS;
import com.atualged.model.Permissao;

@Service
public class PermissaoCtrl {
	@Autowired
	private PermissaoBS pb;
	public List<Permissao> pesquisar(String nome){
		return pb.pesquisar(nome);
	}
}
