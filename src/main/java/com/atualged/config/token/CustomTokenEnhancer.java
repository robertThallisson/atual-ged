package com.atualged.config.token;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.transaction.annotation.Transactional;

import com.atualged.model.Usuario;
import com.atualged.security.UsuarioSistema;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	@Transactional
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario usuario = ((UsuarioSistema) authentication.getPrincipal()).getUsuario();

		Map<String, Object> addInfo = new HashMap<>();

		try {
			if (usuario != null) {
				addInfo.put("login", usuario.getLogin());
				contruir(usuario);

				
				if (usuario.getPerfilUsuario() != null) {
					usuario.setPerfilUsuario(null);
				}
				addInfo.put("usuario", usuario);
				
				if (usuario.getPessoa() != null) {
					addInfo.put("login", usuario.getPessoa().getNome());
				}
				if (usuario.getPessoa() != null) {
					addInfo.put("escritorio", usuario.getPessoa().getEscritorio());
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}

	@Transactional
	private void contruir(Object object) {
		try {
			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if ((field.getType() == List.class || field.getType() == ArrayList.class)) {
					try {
						List<?> a = (List<?>) field.get(object);
						field.set(object, new ArrayList<>(a));
					} catch (Exception e) {
						// TODO: handle exception
						field.set(object, new ArrayList<>());
					}
				}
//				if ((field.getType() == byte[].class)) {
//					field.set(object, null);
//				}
			}
		} catch (Exception e) {
			// TODO: handle exception

		}
	}

}
