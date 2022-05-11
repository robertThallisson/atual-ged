package com.atualged.controller;     
         
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atualged.business.CertificadoBS;
import com.atualged.model.Certificado;   
         
@Service     
public class CertificadoCtrl        { 
	@Autowired         
	CertificadoBS certificadoBs ; 
          
	public Certificado  salvar( Certificado certificado) {        
		return    certificadoBs .salvar(certificado);   
	}       
         
          
         
} 
 