package com.atualged.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atualged.model.Empresa;
import com.atualged.model.PerfilUsuario;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long> {
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO permissao_perfil_usuario(perfil_usuario_id, permissao_id)	VALUES( :p1, :p2 )")
	@Transactional
	public void salvarPerfilDeUsuario(@Param("p1") Long perfilId,@Param("p2")  Long permissaoId );
	
	@Modifying
	@Query(nativeQuery = true, value = "delete from permissao_perfil_usuario where perfil_usuario_id = :p1 and permissao_id = :p2 ")
	@Transactional
	public void remover(@Param("p1") Long perfilId,@Param("p2")  Long permissaoId );
	
	@Modifying
	@Query(nativeQuery = true, value = "delete from permissao_perfil_usuario where perfil_usuario_id = :p1 ")
	@Transactional
	public void removerAll(@Param("p1") Long adm);
	
	@Modifying
	@Query(nativeQuery = true, value = "call deletar_perfil_usuario(:p1) ")
	@Transactional
	public void deletePerfilUsuario(@Param("p1") Long adm);
	
	
	List<PerfilUsuario> findByNomeContainingAndEmpresaOrId(String nome, Empresa empresa,Long id);
	List<PerfilUsuario> findByEmpresaOrId(Empresa empresa, Long id);
	List<PerfilUsuario> findTop10ByEmpresaOrId(Empresa empresa, Long id);

}
