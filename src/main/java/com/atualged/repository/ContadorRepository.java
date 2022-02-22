package com.atualged.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atualged.model.Contador;

public interface ContadorRepository extends JpaRepository<Contador, Long>{

}
