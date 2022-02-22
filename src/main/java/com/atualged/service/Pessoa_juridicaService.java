package com.atualged.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atualged.model.PessoaJuridica;
import com.atualged.repository.Pessoa_juridicaRepository;

@Service
public class Pessoa_juridicaService {
	@Autowired
	private Pessoa_juridicaRepository pessoa_juridicaRepository;
	
	public PessoaJuridica atualizar(Long id, PessoaJuridica pessoa_juridica) {
		PessoaJuridica pessoa_juridicaSalva = pessoa_juridicaRepository.findById(id).get();
		if (pessoa_juridicaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(pessoa_juridica, pessoa_juridicaSalva, "id");
		return pessoa_juridicaRepository.save(pessoa_juridicaSalva);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		PessoaJuridica pessoa_juridicaSalva = buscarPessoa_juridicaPeloCodigo(id);
		pessoa_juridicaSalva.setAtivo(ativo);
		pessoa_juridicaRepository.save(pessoa_juridicaSalva);
	}
	
	private PessoaJuridica buscarPessoa_juridicaPeloCodigo(Long id) {
		PessoaJuridica pessoa_juridicaSalva = pessoa_juridicaRepository.findById(id).get();
		if (pessoa_juridicaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoa_juridicaSalva;
	}


}
