package com.atualged.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atualged.model.Usuario;
import com.atualged.repository.UsuarioRepository;

@Service
public class Usuarioservice {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioSalvo = usuarioRepository.findById(id).get();
		if (usuarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
		return usuarioRepository.save(usuarioSalvo);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Usuario usuarioSalvo = buscarUsuarioPeloCodigo(id);
		usuarioSalvo.setAtivo(ativo);
		usuarioRepository.save(usuarioSalvo);
	}
	
	private Usuario buscarUsuarioPeloCodigo(Long id) {
		Usuario usuarioSalvo = usuarioRepository.findById(id).get();
		if (usuarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return usuarioSalvo;
	}
}
