package com.atualged.repository;     
         
import java.util.List;        
import org.springframework.data.jpa.repository.JpaRepository;        
 
 import com.atualged.model.Escritorio;   
import com.atualged.repository.query.RepositoryQuery;         
 
 public interface EscritorioRepository extends JpaRepository<Escritorio, Long>  , RepositoryQuery<Escritorio>    {
 	List<Escritorio> findByAtivoIsTrue(); 
 	List<Escritorio> findByPessoaJuridicaRazaoSocialContainingIgnoreCase(String value);
 	List<Escritorio> findTop50ByOrderByIdDesc();
  
  
  } 
 