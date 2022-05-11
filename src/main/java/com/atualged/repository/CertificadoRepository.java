package com.atualged.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atualged.model.Certificado;
import com.atualged.model.Empresa;



public interface CertificadoRepository extends JpaRepository<Certificado, Long>{
	Certificado findByEmpresa(Empresa empresa);

}
