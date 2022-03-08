package com.atualged.business;     
         
import org.springframework.stereotype.Service;         
import org.springframework.beans.factory.annotation.Autowired;         
import java.util.List;       
         
import com.atualged.repository.EstadoRepository ;   
import com.atualged.model.Estado;   
         
@Service     
public class EstadoBS       { 
	@Autowired         
	EstadoRepository estadoRepository; 
          
	public Estado  salvar( Estado estado) {        
		return    estadoRepository.save(estado);   
	}       
         
	public List<Estado>  pesquisar( String value) {  
         
     if ((value == null) || (value.trim().equals("")) || value.trim().equals(" ") || value.trim().equals("+=")) {    
     	return estadoRepository.findByAtivoIsTrue(); 
     } else {    
		return estadoRepository.findByNomeContainingIgnoreCase(value);      
     }    
	}       
         
	public List<Estado>  pesquisarUltimosRegistros() {  
		return estadoRepository.findTop40ByOrderByNomeDesc();      
	}       
         
         
         
         
} 
 