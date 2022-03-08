package com.atualged.business;     
         
import org.springframework.stereotype.Service;         
import org.springframework.beans.factory.annotation.Autowired;         
import java.util.List;       
         
import com.atualged.repository.EscritorioRepository ;
import com.atualged.repository.PessoaJuridicaRepository;
import com.atualged.model.Escritorio;   
import com.atualged.repository.filter.Filtro;         
         
@Service     
public class EscritorioBS       { 
	@Autowired         
	EscritorioRepository escritorioRepository; 
          
	@Autowired
	PessoaJuridicaRepository juridicaRepository;
	public Escritorio  salvar( Escritorio escritorio) {        
		if (escritorio.getPessoaJuridica() != null || escritorio.getPessoaJuridica().getId() == null
				|| escritorio.getPessoaJuridica().getId() <= 0) {
			juridicaRepository.save(escritorio.getPessoaJuridica());
		}
		return    escritorioRepository.save(escritorio);   
	}       
         
	public List<Escritorio>  pesquisar( String value) {  
         
     if ((value == null) || (value.trim().equals("")) || value.trim().equals(" ") || value.trim().equals("+=")) {    
     	return escritorioRepository.findByAtivoIsTrue(); 
     } else {    
		return escritorioRepository.findByPessoaJuridicaRazaoSocialContainingIgnoreCase(value);      
     }    
	}       
         
	public List<Escritorio>  pesquisarUltimosRegistros() {  
		return escritorioRepository.findTop50ByOrderByIdDesc();      
	}       
         
         
	  
	public List<Escritorio>  filtrar( Filtro filtro) {  
 		return escritorioRepository.filtrar(filtro);      
	}       
         
         
} 
 