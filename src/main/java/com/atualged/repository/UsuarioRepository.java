package com.atualged.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atualged.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByLoginOrEmail(String login, String email);


}
