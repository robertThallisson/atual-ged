package com.atualged.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atualged.model.Empresa;
import com.atualged.repository.EmpresaRepository;
import com.atualged.repository.PessoaJuridicaRepository;
import com.atualged.repository.filter.Filtro;
import com.atualged.util.AtualGedUtil;

@Service
public class EmpresaBS {
	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	PessoaJuridicaRepository juridicaRepository;

	public Empresa salvar(Empresa empresa) {

		if (empresa.getPessoaJuridica() != null ) {
			juridicaRepository.save(empresa.getPessoaJuridica());
		}
		return empresaRepository.save(empresa);
	}

	public List<Empresa> pesquisar(String value) {

		if ((value == null) || (value.trim().equals("")) || value.trim().equals(" ") || value.trim().equals("+=")) {
			return empresaRepository.findByPessoaJuridicaRazaoSocialContainingIgnoreCaseAndEscritorio(value, AtualGedUtil.getEscritorioTokenRequest());
		} else {
			return empresaRepository.findByEscritorio(AtualGedUtil.getEscritorioTokenRequest());
		}
	}

	public List<Empresa> pesquisarUltimosRegistros() {
		return empresaRepository.findTop50ByEscritorioOrderByIdDesc(AtualGedUtil.getEscritorioTokenRequest());
	}

	public List<Empresa> filtrar(Filtro filtro) {
		return empresaRepository.filtrar(filtro);
	}

}
