package com.atualged.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atualged.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {


	List<Permissao> findByDescricaoContaining(String descricao);
}
