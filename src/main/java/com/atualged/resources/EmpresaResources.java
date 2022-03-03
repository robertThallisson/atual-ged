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
import com.atualged.repository.filter.Filtro;         
         
import com.atualged.controller.EmpresaCtrl ;   
import com.atualged.model.Empresa;   
         
@RestController     
@RequestMapping("/empresa")          
public class EmpresaResources        { 
	@Autowired         
	EmpresaCtrl empresaCtrl; 
          
	@PostMapping        
	@PreAuthorize("hasAuthority('empresa:salvar')")      
	public @ResponseBody ResponseEntity<Empresa>  salvar(@RequestBody Empresa empresa) {        
		return ResponseEntity.ok(empresaCtrl.salvar(empresa));   
	}       
         
	@PostMapping("/pesquisar")       
	@PreAuthorize("hasAuthority('empresa:pesquisar')")       
	public ResponseEntity<List<Empresa>>  pesquisar(@RequestBody String value) {  
		return ResponseEntity.ok(empresaCtrl.pesquisar(value));      
	}       
         
 // pesquisa os ultimos 10 registros, para tela inicial de cada cadastro       
	@GetMapping("/pesquisarultimosregistro")       
	@PreAuthorize("hasAuthority('empresa:pesquisar')")       
	public ResponseEntity<List<Empresa>>  pesquisarUltimosRegistros() {  
 		return ResponseEntity.ok(empresaCtrl.pesquisarUltimosRegistros());      
	}       
         
         
         
	@PostMapping("/filtrar")     
	public ResponseEntity<List<Empresa>>  filtrar(@RequestBody Filtro filtro) {  
 		return ResponseEntity.ok(empresaCtrl.filtrar(filtro));      
	}       
         
} 
 