package com.atualged.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atualged.controller.PerfilUsuarioCtrl;
import com.atualged.controller.PermissaoCtrl;
import com.atualged.model.PerfilUsuario;
import com.atualged.model.Permissao;

@RestController
@RequestMapping("/permissao")
public class PermissaoResources {
	@Autowired
	private PermissaoCtrl permissaoCtrl;
	
	
	@Autowired
	private PerfilUsuarioCtrl perfilUsuarioCtrl;
	@PostMapping(value = "/pesquisarpermissoes" ,produces = "application/json")
	@PreAuthorize("hasAuthority('permissoes:pesquisar')")
	public @ResponseBody ResponseEntity<List<Permissao>>  pesquisarPermissoes(@RequestBody String nome) {	
		return ResponseEntity.ok(permissaoCtrl.pesquisar(nome));
	}
	
	
	/*                    COLOCAR METODOS REFERENTES A PERFIL USUARIO DESTE PONTO PARA BAIXO                                   */
	@PostMapping(value = "/salvarperfilusuario" ,produces = "application/json")
	//@PreAuthorize("hasAuthority('permissoes:salvar')")
	public @ResponseBody ResponseEntity<PerfilUsuario>  salvar(@RequestBody PerfilUsuario perfilUsuario) {
		perfilUsuarioCtrl.salvar(perfilUsuario);
		return ResponseEntity.ok(perfilUsuario);

	}
/**/	
	@PostMapping(value = "/pesquisarperfilusuario" ,produces = "application/json")
	@PreAuthorize("hasAuthority('permissoes:pesquisar')")
	public @ResponseBody ResponseEntity<List<PerfilUsuario>>  pesquisarPerfilUsuario(@RequestBody String nome) {
		return ResponseEntity.ok(perfilUsuarioCtrl.pesquisar(nome));
	}
	
	@GetMapping(value = "/pesquisarultimosregistro" ,produces = "application/json")
	@PreAuthorize("hasAuthority('permissoes:pesquisar')")
	public @ResponseBody ResponseEntity<List<PerfilUsuario>>  pesquisarPerfilUsuario1() {
		return ResponseEntity.ok(perfilUsuarioCtrl.pesquisarUltimosRegistros());
	}
		
	@PostMapping(value = "/excluirperfilusuario" ,produces = "application/json")
	@PreAuthorize("hasAuthority('permissoes:salvar')")
	public @ResponseBody ResponseEntity<PerfilUsuario>  excluirPerfil(@RequestBody PerfilUsuario perfilUsuario) {
		perfilUsuarioCtrl.deletar(perfilUsuario);
		return ResponseEntity.ok(perfilUsuario);

	}
	
	
}
