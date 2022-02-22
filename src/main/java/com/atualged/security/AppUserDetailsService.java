package com.atualged.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.atualged.model.Usuario;
import com.atualged.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository ur;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Usuario> optional =  ur.findByLoginOrEmail(username, username);
		Usuario usuario = optional.orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha incorretos"));
		return new UserSistema(usuario , getPermissoes(usuario)) ;
	}
	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		// TODO Auto-generated method stub
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		usuario.getPermissoes().forEach( p -> authorities.add(new  SimpleGrantedAuthority(p.getTipoPermissao())));
		return authorities;
	}

}
