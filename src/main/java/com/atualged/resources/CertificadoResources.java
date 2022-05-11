package com.atualged.resources;     
         
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atualged.controller.CertificadoCtrl;
import com.atualged.model.Certificado;   
         
@RestController     
@RequestMapping("/certificado")          
public class CertificadoResources        { 
	@Autowired         
	CertificadoCtrl certificadoCtrl; 
          
	@PostMapping        
	//@PreAuthorize("hasAuthority('certificado:salvar')")      
	public @ResponseBody ResponseEntity<Certificado>  salvar(@RequestBody Certificado certificado) {        
		return ResponseEntity.ok(certificadoCtrl.salvar(certificado));   
	}       
            

         
} 
 