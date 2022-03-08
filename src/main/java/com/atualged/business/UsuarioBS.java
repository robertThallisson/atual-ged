package com.atualged.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atualged.model.Usuario;
import com.atualged.repository.PessoaRepository;
import com.atualged.repository.UsuarioRepository;

@Service
public class UsuarioBS  {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	

	public void salvar(Usuario usuario){
		
		pessoaRepository.save(usuario.getPessoa());
		usuarioRepository.save(usuario);
	}
	
	public void salvarSoUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public String getSenhaByUsuario(Usuario usuario) {
		if(usuario == null) {
			return null;
		}
		return usuarioRepository.getSenhaByNomeOrId(usuario.getId(), usuario.getSenha());
	}
	
	public Usuario getByCPF(String cpf) {
		return usuarioRepository.getByCPF(cpf);
	}
	
	public byte[] getImagemUsuario(Long id) {
		return usuarioRepository.getImagemUsuario(id);
	}

	public List<Usuario> pesquisar(String value) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByLoginContainingOrEmailContaining(value, value);
	}

	public List<Usuario> pesquisarUltimoRegistro() {
		// TODO Auto-generated method stub
		return usuarioRepository.findTop10ByAtivoIsTrueOrderByIdDesc();
	}
}
