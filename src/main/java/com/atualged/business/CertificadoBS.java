package com.atualged.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atualged.model.Certificado;
import com.atualged.repository.CertificadoRepository;



@Service
public class CertificadoBS {
	@Autowired
	CertificadoRepository certificadoRepository;


	public Certificado salvar(Certificado certificado) {
		return certificadoRepository.save(certificado);
	}

	public Certificado getCertificadoEmpresa() {

		return null;//return certificadoRepository.findByEmpresa(null);
	}

}
