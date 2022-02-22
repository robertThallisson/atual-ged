package com.atualged.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atualged.model.Escritorio;
import com.atualged.repository.EscritorioRepository;

@Service
public class EscritorioService {
	
	@Autowired
	private EscritorioRepository escritorioRepository;
	
	public Escritorio atualizar(Long id, Escritorio escritorio) {
		Escritorio escritorioSalvo = escritorioRepository.findById(id).get();
		if (escritorioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(escritorio, escritorioSalvo, "id");
		return escritorioRepository.save(escritorioSalvo);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Escritorio escritorioSalvo = buscarEscritorioPeloCodigo(id);
		escritorioSalvo.setAtivo(ativo);
		escritorioRepository.save(escritorioSalvo);
	}
	
	private Escritorio buscarEscritorioPeloCodigo(Long id) {
		Escritorio escritorioSalvo = escritorioRepository.findById(id).get();
		if (escritorioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return escritorioSalvo;
	}

}
