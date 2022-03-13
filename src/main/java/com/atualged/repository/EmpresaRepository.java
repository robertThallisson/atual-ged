package com.atualged.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atualged.model.Empresa;
import com.atualged.model.Escritorio;
import com.atualged.repository.query.RepositoryQuery;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>, RepositoryQuery<Empresa> {
	List<Empresa> findByAtivoIsTrue();

	List<Empresa> findByPessoaJuridicaRazaoSocialContainingIgnoreCaseAndEscritorio(String value, Escritorio escritorio);
	List<Empresa> findByEscritorio( Escritorio escritorio);
	List<Empresa> findTop50ByEscritorioOrderByIdDesc( Escritorio escritorio);
	
	@Query(nativeQuery = true, value =  "select pej.logo from empresa emp  inner join pessoa_juridica pej on emp.pessoa_juridica_id = pej.id where id = :p1 ")
	public byte[] getImagemEmpresa(@Param("p1") Long id);

}
