package com.atualged.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atualged.model.Fornecedor;
import com.atualged.repository.FornecedorRepository;

@Service
public class FornecedorService {
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public Fornecedor atualizar(Long id, Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = fornecedorRepository.findById(id).get();
		if (fornecedorSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(fornecedor, fornecedorSalvo, "id");
		return fornecedorRepository.save(fornecedorSalvo);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Fornecedor fornecedorSalvo = buscarFornecedorPeloCodigo(id);
		fornecedorSalvo.setAtivo(ativo);
		fornecedorRepository.save(fornecedorSalvo);
	}
	
	private Fornecedor buscarFornecedorPeloCodigo(Long id) {
		Fornecedor fornecedorSalvo = fornecedorRepository.findById(id).get();
		if (fornecedorSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return fornecedorSalvo;
	}


}
