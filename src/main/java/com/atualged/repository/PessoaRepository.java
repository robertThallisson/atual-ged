package com.atualged.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atualged.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
