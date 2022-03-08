package com.atualged.repository;     
         
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atualged.model.Cidade;
import com.atualged.repository.query.RepositoryQuery;         
 
 public interface CidadeRepository extends JpaRepository<Cidade, Long>  , RepositoryQuery<Cidade>    {
 	List<Cidade> findByAtivoIsTrue(); 
 	List<Cidade> findByNomeContainingIgnoreCaseOrCodigoIbge(String value,String value2);
 	List<Cidade> findTop50ByOrderByIdDesc();
  
  
  } 
 