package com.atualged.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atualged.model.Estado;
import com.atualged.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado atualizar(Long id, Estado estado) {
		Estado estadoSalvo = estadoRepository.findById(id).get();
		if (estadoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(estado, estadoSalvo, "id");
		return estadoRepository.save(estadoSalvo);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Estado estadoSalvo = buscarEstadoPeloCodigo(id);
		estadoSalvo.setAtivo(ativo);
		estadoRepository.save(estadoSalvo);
	}
	
	private Estado buscarEstadoPeloCodigo(Long id) {
		Estado estadoSalvo = estadoRepository.findById(id).get();
		if (estadoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return estadoSalvo;
	}

}
