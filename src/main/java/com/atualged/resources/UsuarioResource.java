package com.atualged.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atualged.controller.UsuarioCtrl;
import com.atualged.model.Pessoa;
import com.atualged.model.Usuario;


@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioCtrl usuarioCtrl;
	
	@PostMapping("/pesquisar")
	@PreAuthorize("hasAuthority('usuario:pesquisar')")
	public ResponseEntity<List<Usuario>>  pesquisar(@RequestBody String value) {		
		return ResponseEntity.ok(usuarioCtrl.pesquisar(value));
	}
	
	@GetMapping("/pesquisarultimosregistro")
	@PreAuthorize("hasAuthority('usuario:pesquisar')")
	public ResponseEntity<List<Usuario>>  pesquisarUltimoRegistro() {		
		return ResponseEntity.ok(usuarioCtrl.pesquisarUltimoRegistro());	
	}
	
	@PostMapping        
	@PreAuthorize("hasAuthority('usuario:salvar')")      
	public @ResponseBody ResponseEntity<Usuario>  salvar(@RequestBody Usuario usuario) {        
		return ResponseEntity.ok(usuarioCtrl.salvar(usuario));   
	} 



	
	/*          NUNCA COLOCAR PERMISSAO DE ACESSO AQUI POIS TODO USUARIO PODE ALTERAR E TER ACESSO A SUA SENHA */
	@PostMapping("/alterarsenha")
	public ResponseEntity<Usuario> alterarSenha(@RequestBody Usuario usuario) {
		usuarioCtrl.salvarSoUsuario(usuario);;
		return ResponseEntity.ok(usuario);
	}
	@PostMapping("/confirmasenha")
	public ResponseEntity<Boolean> confirmaSenha(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(Boolean.valueOf(usuarioCtrl.confirmarSenha(usuario))) ;
		
	}
	
	@PostMapping("/esqueceusenha")
	public ResponseEntity<Boolean> esqueceuSenha(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(Boolean.valueOf(usuarioCtrl.esqueceuSenha(usuario))) ;
		
	}
	
	@GetMapping("/imagemusuario/{id}")
	public ResponseEntity<Usuario> getImagemUsuario(@PathVariable("id")  Long id) {
		Usuario usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
		usuario.getPessoa().setFoto(usuarioCtrl.getImagemUsuario(id));
		return ResponseEntity.ok(usuario) ;
		
	}

}
