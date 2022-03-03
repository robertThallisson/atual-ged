package com.atualged.controller;     
         
import org.springframework.stereotype.Service;         
import org.springframework.beans.factory.annotation.Autowired;         
import java.util.List;      
         
import com.atualged.business.EscritorioBS ;   
import com.atualged.model.Escritorio;   
import com.atualged.repository.filter.Filtro;         
         
@Service     
public class EscritorioCtrl        { 
	@Autowired         
	EscritorioBS escritorioBs ; 
          
	public Escritorio  salvar( Escritorio escritorio) {        
		return    escritorioBs .salvar(escritorio);   
	}       
         
	public List<Escritorio>  pesquisar( String value) {  
		return escritorioBs .pesquisar(value);      
	}       
         
	public List<Escritorio>  pesquisarUltimosRegistros() {  
 		return escritorioBs .pesquisarUltimosRegistros();      
	}       
         
         
	  
	public List<Escritorio>  filtrar( Filtro filtro) {  
 		return escritorioBs .filtrar(filtro);      
	}       
         
         
} 
 