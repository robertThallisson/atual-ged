package com.atualged.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atualged.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByLoginOrEmail(String login, String email);
	public Optional<Usuario> findByLogin(String login);

	
	public Page<Usuario> findByLoginContaining(String login, Pageable pageable);
	List<Usuario> findByLoginContainingOrEmailContaining(String login, String email);
	public List<Usuario> findByLoginContaining(String login);
	public List<Usuario> findByAtivoIsTrue();
	public List<Usuario> findTop10ByAtivoIsTrueOrderByIdDesc();
	
	/* METODOS USANDO NATIVE QUERY PARA OTIMIZAÇÃO*/
	@Query(nativeQuery = true, value =  "select senha from usuario where id = :p1 or login like :p2")
	public String getSenhaByNomeOrId(@Param("p1") Long id, @Param("p2") String login);
	
	@Query(nativeQuery = true, value =  "select * from usuario usu inner join pessoa pes on pes.id = usu.pessoa_id  where cpf = :p1 ")
	public Usuario getByCPF(@Param("p1") String cpf);
	
	@Query(nativeQuery = true, value =  "select pes.foto from usuario usu inner join pessoa pes on pes.id = usu.pessoa_id  where usu.id = :p1 ")
	public byte[] getImagemUsuario(@Param("p1") Long id);
}
