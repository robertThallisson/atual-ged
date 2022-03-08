package com.atualged.controller;     
         
import org.springframework.stereotype.Service;         
import org.springframework.beans.factory.annotation.Autowired;         
import java.util.List;      
         
import com.atualged.business.EstadoBS ;   
import com.atualged.model.Estado;   
         
@Service     
public class EstadoCtrl        { 
	@Autowired         
	EstadoBS estadoBs ; 
          
	public Estado  salvar( Estado estado) {        
		return    estadoBs .salvar(estado);   
	}       
         
	public List<Estado>  pesquisar( String value) {  
		return estadoBs .pesquisar(value);      
	}       
         
	public List<Estado>  pesquisarUltimosRegistros() {  
 		return estadoBs .pesquisarUltimosRegistros();      
	}       
         
         
         
         
} 
 