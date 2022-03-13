package com.atualged.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atualged.model.PerfilUsuario;
import com.atualged.model.Permissao;
import com.atualged.repository.PerfilUsuarioRepository;
import com.atualged.util.AtualGedUtil;



@Service
public class PerfilUsuarioBS {
	@Autowired
	private PerfilUsuarioRepository pur;

	
	public void salvar(PerfilUsuario perfilUsuario) {
		if (perfilUsuario.getId() != null && perfilUsuario.getId() > 0) {
			pur.removerAll(perfilUsuario.getId());
		}
		pur.save(perfilUsuario);
	}

	public void salvarPermissoesPerfil(List<Permissao> permissoes, PerfilUsuario perfilUsuario) {
		for (Permissao permissao : permissoes) {
			pur.salvarPerfilDeUsuario(perfilUsuario.getId(), permissao.getId());
		}
	}

	public List<PerfilUsuario> pesquisar(String nome) {
		if (true) {
			return pur.findByEscritorioIsNull();
		} else {
			if (nome == null || nome.trim().equals("")) {
				return pur.findByEscritorioOrId(AtualGedUtil.getEscritorioTokenRequest(), 2l);
			} else {
				return pur.findByNomeContainingAndEscritorioOrId(nome, AtualGedUtil.getEscritorioTokenRequest(), 2l);
			}
		}

	}

	public List<PerfilUsuario> pesquisarUltimosRegistros() {
		if (true) {
			return pur.findByEscritorioIsNull();
		} else {
			return pur.findTop10ByEscritorioOrId( AtualGedUtil.getEscritorioTokenRequest(), 2l);
		}

	}

	public void deletar(PerfilUsuario perfilUsuario) {
		pur.deletePerfilUsuario(perfilUsuario.getId());
	}
	
}
