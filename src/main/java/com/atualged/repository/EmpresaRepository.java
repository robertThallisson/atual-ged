package com.atualged.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atualged.model.Empresa;
import com.atualged.repository.query.RepositoryQuery;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>, RepositoryQuery<Empresa> {
	List<Empresa> findByAtivoIsTrue();

	List<Empresa> findByPessoaJuridicaNomeFantasiaContainingIgnoreCase(String value);

	List<Empresa> findTop50ByOrderByIdDesc();

}
