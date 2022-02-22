package com.atualged.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.atualged.model.Usuario;
import com.atualged.repository.PerfilUsuarioRepository;
import com.atualged.repository.UsuarioRepository;


@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PerfilUsuarioRepository pu;


	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		if (login.equals("admin")) {
			Usuario aux = new Usuario();
			aux.setEmail(login);
			aux.setLogin("administrador");
			aux.setSenha("{bcrypt}$2a$10$1XOX0SM3r/UU7hshlBp0GODwWi7IsfOcSPRGOhmBxICYnCSY0r8Ba");
			
			aux.setPerfilUsuario(pu.findById(1l).get());
			//aux.getPerfilUsuario().setPermissoes(pr.findAll());
			return new UsuarioSistema(aux, getPermissoes(aux));
		}
		Optional<Usuario> usuarioOptional = usuarioRepository.findByLoginOrEmail(login, login);
		Usuario usuario = usuarioOptional
				.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}

	@Transactional
	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		try {
			if (usuario.getPerfilUsuario() != null) {
				usuario.getPerfilUsuario().getPermissoes()
						.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao())));
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			// TODO: handle exception
		}
		return authorities;
	}
}
