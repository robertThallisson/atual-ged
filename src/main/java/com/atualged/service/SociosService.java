package com.atualged.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atualged.model.Socio;
import com.atualged.repository.SociosRepository;

@Service
public class SociosService {
	
	@Autowired
	private SociosRepository sociosRepository;
	
	public Socio atualizar(Long id, Socio socios) {
		Socio sociosSalvo = sociosRepository.findById(id).get();
		if (sociosSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(socios, sociosSalvo, "id");
		return sociosRepository.save(sociosSalvo);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Socio sociosSalvo = buscarSociosPeloCodigo(id);
		sociosSalvo.setAtivo(ativo);
		sociosRepository.save(sociosSalvo);
	}
	
	private Socio buscarSociosPeloCodigo(Long id) {
		Socio sociosSalvo = sociosRepository.findById(id).get();
		if (sociosSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return sociosSalvo;
	}

}
