package com.atualged.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atualged.model.Permissao;
import com.atualged.repository.PermissaoRepository;

@Service
public class PermissaoBS {
	@Autowired
	private PermissaoRepository pr;
	
	public List<Permissao> pesquisar(String nome){
		if (nome == null || nome.trim().equals("")) {
			return pr.findAll();
		} else {
			return pr.findByDescricaoContaining(nome);
		}
	}
}
