package com.atualged.resources;     
         
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atualged.controller.CidadeCtrl ;
import com.atualged.model.Cidade;
import com.atualged.repository.filter.Filtro;   
         
@RestController     
@RequestMapping("/cidade")          
public class CidadeResources        { 
	@Autowired         
	CidadeCtrl cidadeCtrl; 
          
	@PostMapping        
//	@PreAuthorize("hasAuthority('cidade:salvar')")      
	public @ResponseBody ResponseEntity<Cidade>  salvar(@RequestBody Cidade cidade) {        
		return ResponseEntity.ok(cidadeCtrl.salvar(cidade));   
	}       
         
	@PostMapping("/pesquisar")       
//	@PreAuthorize("hasAuthority('cidade:pesquisar')")       
	public ResponseEntity<List<Cidade>>  pesquisar(@RequestBody String value) {  
		return ResponseEntity.ok(cidadeCtrl.pesquisar(value));      
	}       
         
 // pesquisa os ultimos 10 registros, para tela inicial de cada cadastro       
	@GetMapping("/pesquisarultimosregistro")       
//	@PreAuthorize("hasAuthority('cidade:pesquisar')")       
	public ResponseEntity<List<Cidade>>  pesquisarUltimosRegistros() {  
 		return ResponseEntity.ok(cidadeCtrl.pesquisarUltimosRegistros());      
	}       
         
         
         
	@PostMapping("/filtrar")     
	public ResponseEntity<List<Cidade>>  filtrar(@RequestBody Filtro filtro) {  
 		return ResponseEntity.ok(cidadeCtrl.filtrar(filtro));      
	}       
         
} 
 