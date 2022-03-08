package com.atualged.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atualged.model.Cidade;
import com.atualged.repository.CidadeRepository;
import com.atualged.repository.filter.Filtro;

@Service
public class CidadeBS {
	@Autowired
	CidadeRepository cidadeRepository;

	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	public List<Cidade> pesquisar(String value) {

		if ((value == null) || (value.trim().equals("")) || value.trim().equals(" ") || value.trim().equals("+=")) {
			return cidadeRepository.findByAtivoIsTrue();
		} else {
			return cidadeRepository.findByNomeContainingIgnoreCaseOrCodigoIbge(value, value);
		}
	}

	public List<Cidade> pesquisarUltimosRegistros() {
		return cidadeRepository.findTop50ByOrderByIdDesc();
	}

	public List<Cidade> filtrar(Filtro filtro) {
		return cidadeRepository.filtrar(filtro);
	}

}
