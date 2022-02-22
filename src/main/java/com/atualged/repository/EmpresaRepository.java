package com.atualged.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atualged.model.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}
