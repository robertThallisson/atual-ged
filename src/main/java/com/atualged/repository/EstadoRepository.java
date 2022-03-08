package com.atualged.repository;     
         
import java.util.List;        
import org.springframework.data.jpa.repository.JpaRepository;        
 
 import com.atualged.model.Estado;   
 
 public interface EstadoRepository extends JpaRepository<Estado, Long>      {
 	List<Estado> findByAtivoIsTrue(); 
 	List<Estado> findByNomeContainingIgnoreCase(String value);
 	List<Estado> findTop40ByOrderByNomeDesc();
  
  
  } 
 