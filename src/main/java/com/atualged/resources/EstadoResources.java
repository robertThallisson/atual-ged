package com.atualged.resources;     
         
import java.util.List;        
import org.springframework.beans.factory.annotation.Autowired;         
import org.springframework.http.ResponseEntity;         
import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.RestController;   
import org.springframework.web.bind.annotation.RequestMapping;     
import org.springframework.web.bind.annotation.ResponseBody;      
import org.springframework.web.bind.annotation.RequestBody;         
import org.springframework.web.bind.annotation.PostMapping;         
import org.springframework.web.bind.annotation.GetMapping;         
         
import com.atualged.controller.EstadoCtrl ;   
import com.atualged.model.Estado;   
         
@RestController     
@RequestMapping("/estado")          
public class EstadoResources        { 
	@Autowired         
	EstadoCtrl estadoCtrl; 
          
	@PostMapping        
	@PreAuthorize("hasAuthority('estado:salvar')")      
	public @ResponseBody ResponseEntity<Estado>  salvar(@RequestBody Estado estado) {        
		return ResponseEntity.ok(estadoCtrl.salvar(estado));   
	}       
         
	@PostMapping("/pesquisar")       
	@PreAuthorize("hasAuthority('estado:pesquisar')")       
	public ResponseEntity<List<Estado>>  pesquisar(@RequestBody String value) {  
		return ResponseEntity.ok(estadoCtrl.pesquisar(value));      
	}       
         
 // pesquisa os ultimos 10 registros, para tela inicial de cada cadastro       
	@GetMapping("/pesquisarultimosregistro")       
	@PreAuthorize("hasAuthority('estado:pesquisar')")       
	public ResponseEntity<List<Estado>>  pesquisarUltimosRegistros() {  
 		return ResponseEntity.ok(estadoCtrl.pesquisarUltimosRegistros());      
	}       
         
         
         
         
} 
 