package com.atualged.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atualged.business.UsuarioBS;
import com.atualged.exception.AtualGedException;
import com.atualged.model.Usuario;
import com.atualged.util.AtualGedUtil;
import com.atualged.util.CPFCNPJUtil;

@Service
public class UsuarioCtrl {
	@Autowired
	private UsuarioBS bs;

	final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	public Usuario salvar(Usuario usuario) {
		tratarSenha(usuario);
		bs.salvar(usuario);
		
		return usuario;
	}

	public void salvarSoUsuario(Usuario usuario) {
		tratarEncodamentoSenha(usuario);
		bs.salvarSoUsuario(usuario);
	}
	
	public boolean confirmarSenha(Usuario usuario) {
		if (usuario == null || usuario.getSenha() == null) {
			return false;
		}

		boolean retorno = AtualGedUtil.senhasIguais(usuario.getSenha(), bs.getSenhaByUsuario(usuario));
		if (retorno) {
			return retorno;
		} else {
			throw new AtualGedException("Senha Incorreta", "Senha Incoreta");
		}
	}
	
	public Boolean esqueceuSenha(Usuario usuario) {
		if (usuario == null || usuario.getPessoa() == null || usuario.getSenha() == null
				|| usuario.getSenha().trim().equals("") || usuario.getPessoa().getCpf() == null
				|| usuario.getPessoa().getCpf().trim().equals("")) {
			throw new AtualGedException("dados insuficiente para alterar a senha", "falha ao acessar usuario");
		}
		usuario.getPessoa().setCpf(CPFCNPJUtil.formatCPForCPNJ(usuario.getPessoa().getCpf()));
		Usuario usuarioBanco = bs.getByCPF(usuario.getPessoa().getCpf());
		if (usuarioBanco != null) {
			usuarioBanco.setSenha(usuario.getSenha());
			salvarSoUsuario(usuarioBanco);
			return true;
		} else {
			throw new AtualGedException("CPF Não encotrado", "falha ao acessar usuario");
		}
	}
	
	public byte[] getImagemUsuario(Long id) {
		return bs.getImagemUsuario(id);
	}

	public void tratarSenha(Usuario usuario) {
		if (usuario.getSenha() == null || usuario.getSenha().trim().equals("")) {
			usuario.setSenha("abc123");
			tratarEncodamentoSenha(usuario);
		} else {
			tratarEncodamentoSenha(usuario);
		}
	}
	
	public void tratarEncodamentoSenha(Usuario usuario) {
		if (encoder.upgradeEncoding(usuario.getSenha())) {
			usuario.setSenha(encoder.encode(usuario.getSenha()));
		}
	}

	public List<Usuario> pesquisar(String value) {
		// TODO Auto-generated method stub
		return bs.pesquisar(value);
	}

	public List<Usuario> pesquisarUltimoRegistro() {
		// TODO Auto-generated method stub
		return bs.pesquisarUltimoRegistro();
	}
}
