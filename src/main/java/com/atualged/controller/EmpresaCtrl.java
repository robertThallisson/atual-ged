package com.atualged.controller;     
         
import org.springframework.stereotype.Service;         
import org.springframework.beans.factory.annotation.Autowired;         
import java.util.List;      
         
import com.atualged.business.EmpresaBS ;   
import com.atualged.model.Empresa;   
import com.atualged.repository.filter.Filtro;         
         
@Service     
public class EmpresaCtrl        { 
	@Autowired         
	EmpresaBS empresaBs ; 
          
	public Empresa  salvar( Empresa empresa) {        
		return    empresaBs .salvar(empresa);   
	}       
         
	public List<Empresa>  pesquisar( String value) {  
		return empresaBs .pesquisar(value);      
	}       
         
	public List<Empresa>  pesquisarUltimosRegistros() {  
 		return empresaBs .pesquisarUltimosRegistros();      
	}       
         
         
	  
	public List<Empresa>  filtrar( Filtro filtro) {  
 		return empresaBs .filtrar(filtro);      
	}       
         
         
} 
 