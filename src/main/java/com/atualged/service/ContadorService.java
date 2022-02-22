package com.atualged.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atualged.model.Contador;
import com.atualged.repository.ContadorRepository;

@Service
public class ContadorService {
	@Autowired
	private ContadorRepository contadorRepository;
	
	public Contador atualizar(Long id, Contador contador) {
		Contador contadorSalvo = contadorRepository.findById(id).get();
		if (contadorSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(contador, contadorSalvo, "id");
		return contadorRepository.save(contadorSalvo);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Contador contadorSalvo = buscarContadorPeloCodigo(id);
		contadorSalvo.setAtivo(ativo);
		contadorRepository.save(contadorSalvo);
	}
	
	private Contador buscarContadorPeloCodigo(Long id) {
		Contador contadorSalvo = contadorRepository.findById(id).get();
		if (contadorSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return contadorSalvo;
	}

}
