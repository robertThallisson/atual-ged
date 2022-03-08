package com.atualged.controller;     
         
import org.springframework.stereotype.Service;         
import org.springframework.beans.factory.annotation.Autowired;         
import java.util.List;      
         
import com.atualged.business.CidadeBS ;   
import com.atualged.model.Cidade;   
import com.atualged.repository.filter.Filtro;         
         
@Service     
public class CidadeCtrl        { 
	@Autowired         
	CidadeBS cidadeBs ; 
          
	public Cidade  salvar( Cidade cidade) {        
		return    cidadeBs .salvar(cidade);   
	}       
         
	public List<Cidade>  pesquisar( String value) {  
		return cidadeBs .pesquisar(value);      
	}       
         
	public List<Cidade>  pesquisarUltimosRegistros() {  
 		return cidadeBs .pesquisarUltimosRegistros();      
	}       
         
         
	  
	public List<Cidade>  filtrar( Filtro filtro) {  
 		return cidadeBs .filtrar(filtro);      
	}       
         
         
} 
 