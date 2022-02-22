package com.atualged.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.atualged.model.Permissao;
import com.atualged.repository.PermissoesRepository;

public class PermissoesService {
	@Autowired
	private PermissoesRepository permissoesRepository;
	
	public Permissao atualizar(Long id, Permissao permissoes) {
		Permissao permissoesSalva = permissoesRepository.findById(id).get();
		if (permissoesSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(permissoes, permissoesSalva, "id");
		return permissoesRepository.save(permissoesSalva);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Permissao permissoesSalva = buscarPermissoesPeloCodigo(id);
		permissoesSalva.setAtivo(ativo);
		permissoesRepository.save(permissoesSalva);
	}
	
	private Permissao buscarPermissoesPeloCodigo(Long id) {
		Permissao permissoesSalva = permissoesRepository.findById(id).get();
		if (permissoesSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return permissoesSalva;
	}

}
