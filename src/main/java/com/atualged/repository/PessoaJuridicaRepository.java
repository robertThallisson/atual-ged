package com.atualged.repository;     
         
import org.springframework.data.jpa.repository.JpaRepository;

import com.atualged.model.Empresa;
import com.atualged.model.PessoaJuridica;
import com.atualged.repository.query.RepositoryQuery;         
 
 public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Empresa>  , RepositoryQuery<PessoaJuridica>    {

  
  
  } 
 