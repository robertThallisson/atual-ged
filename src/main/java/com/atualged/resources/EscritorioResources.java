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

import com.atualged.controller.EscritorioCtrl ;
import com.atualged.model.Escritorio;
import com.atualged.repository.filter.Filtro;   
         
@RestController     
@RequestMapping("/escritorio")          
public class EscritorioResources        { 
	@Autowired         
	EscritorioCtrl escritorioCtrl; 
          
	@PostMapping        
//	@PreAuthorize("hasAuthority('escritorio:salvar')")      
	public @ResponseBody ResponseEntity<Escritorio>  salvar(@RequestBody Escritorio escritorio) {        
		return ResponseEntity.ok(escritorioCtrl.salvar(escritorio));   
	}       
         
	@PostMapping("/pesquisar")       
//	@PreAuthorize("hasAuthority('escritorio:pesquisar')")       
	public ResponseEntity<List<Escritorio>>  pesquisar(@RequestBody String value) {  
		return ResponseEntity.ok(escritorioCtrl.pesquisar(value));      
	}       
         
 // pesquisa os ultimos 10 registros, para tela inicial de cada cadastro       
	@GetMapping("/pesquisarultimosregistro")       
//	@PreAuthorize("hasAuthority('escritorio:pesquisar')")       
	public ResponseEntity<List<Escritorio>>  pesquisarUltimosRegistros() {  
 		return ResponseEntity.ok(escritorioCtrl.pesquisarUltimosRegistros());      
	}       
         
         
         
	@PostMapping("/filtrar")     
	public ResponseEntity<List<Escritorio>>  filtrar(@RequestBody Filtro filtro) {  
 		return ResponseEntity.ok(escritorioCtrl.filtrar(filtro));      
	}       
         
} 
 