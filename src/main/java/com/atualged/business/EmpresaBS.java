package com.atualged.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atualged.model.Empresa;
import com.atualged.repository.EmpresaRepository;
import com.atualged.repository.PessoaJuridicaRepository;
import com.atualged.repository.filter.Filtro;

@Service
public class EmpresaBS {
	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	PessoaJuridicaRepository juridicaRepository;

	public Empresa salvar(Empresa empresa) {

		if (empresa.getPessoaJuridica() != null || empresa.getPessoaJuridica().getId() == null
				|| empresa.getPessoaJuridica().getId() <= 0) {
			juridicaRepository.save(empresa.getPessoaJuridica());
		}
		return empresaRepository.save(empresa);
	}

	public List<Empresa> pesquisar(String value) {

		if ((value == null) || (value.trim().equals("")) || value.trim().equals(" ") || value.trim().equals("+=")) {
			return null;// empresaRepository.findByAtivoIsTrueAndEmpresa(empresaBean.getEmpresa());
		} else {
			return null;// empresaRepository.ContainingIgnoreCaseAndEmpresa(value,
						// empresaBean.getEmpresa());
		}
	}

	public List<Empresa> pesquisarUltimosRegistros() {
		return null;// empresaRepository.AndEmpresaOrderByIdDesc(empresaBean.getEmpresa());
	}

	public List<Empresa> filtrar(Filtro filtro) {
		return empresaRepository.filtrar(filtro);
	}

}
