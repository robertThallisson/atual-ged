package com.atualged.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atualged.model.Empresa;
import com.atualged.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public Empresa atualizar(Long id, Empresa empresa) {
		Empresa empresaSalva = empresaRepository.findById(id).get();
		if (empresaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(empresa, empresaSalva, "id");
		return empresaRepository.save(empresaSalva);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Empresa empresaSalva = buscarEmpresaPeloCodigo(id);
		empresaSalva.setAtivo(ativo);
		empresaRepository.save(empresaSalva);
	}
	
	private Empresa buscarEmpresaPeloCodigo(Long id) {
		Empresa empresaSalva = empresaRepository.findById(id).get();
		if (empresaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return empresaSalva;
	}


}
