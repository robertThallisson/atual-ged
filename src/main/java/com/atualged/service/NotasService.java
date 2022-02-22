package com.atualged.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atualged.model.Notas;
import com.atualged.repository.NotasRepository;

@Service
public class NotasService {
	
	@Autowired
	private NotasRepository notasRepository;
	
	public Notas atualizar(Long id, Notas notas) {
		Notas notasSalva = notasRepository.findById(id).get();
		if (notasSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(notas, notasSalva, "id");
		return notasRepository.save(notasSalva);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Notas notasSalva = buscarNotasPeloCodigo(id);
		notasSalva.setAtivo(ativo);
		notasRepository.save(notasSalva);
	}
	
	private Notas buscarNotasPeloCodigo(Long id) {
		Notas notasSalva = notasRepository.findById(id).get();
		if (notasSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return notasSalva;
	}

}
