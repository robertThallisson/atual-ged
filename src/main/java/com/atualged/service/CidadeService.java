package com.atualged.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atualged.model.Cidade;
import com.atualged.repository.CidadeRepository;


@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade atualizar(Long id, Cidade cidade) {
		Cidade cidadeSalva = cidadeRepository.findById(id).get();
		if (cidadeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(cidade, cidadeSalva, "id");
		return cidadeRepository.save(cidadeSalva);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Cidade cidadeSalva = buscarCidadePeloCodigo(id);
		cidadeSalva.setAtivo(ativo);
		cidadeRepository.save(cidadeSalva);
	}
	
	private Cidade buscarCidadePeloCodigo(Long id) {
		Cidade cidadeSalva = cidadeRepository.findById(id).get();
		if (cidadeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return cidadeSalva;
	}


}
