package com.atualged.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atualged.model.Certificado;
import com.atualged.repository.CertificadoRepository;

@Service
public class CertificadoService {
	
	@Autowired
	private CertificadoRepository certificadoRepository;
	
	public Certificado atualizar(Long id, Certificado certificado) {
		Certificado certificadoSalvo = certificadoRepository.findById(id).get();
		if (certificadoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(certificado, certificadoSalvo, "id");
		return certificadoRepository.save(certificadoSalvo);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Certificado certificadoSalvo = buscarCertificadoPeloCodigo(id);
		certificadoSalvo.setAtivo(ativo);
		certificadoRepository.save(certificadoSalvo);
	}
	
	private Certificado buscarCertificadoPeloCodigo(Long id) {
		Certificado certificadoSalvo = certificadoRepository.findById(id).get();
		if (certificadoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return certificadoSalvo;
	}

}
