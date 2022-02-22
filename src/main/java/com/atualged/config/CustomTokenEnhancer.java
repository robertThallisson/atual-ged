package com.atualged.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.atualged.model.Usuario;
import com.atualged.security.UserSistema;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		Usuario usuario = ((UserSistema) authentication.getPrincipal()).getUsuario();

		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("nome", usuario.getLogin());
		try {
			for (Field field : usuario.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if ((field.getType() == List.class || field.getType() == ArrayList.class)) {
					try {
						List<?> a = (List<?>) field.get(usuario);
						field.set(usuario, new ArrayList<>(a));
					} catch (Exception e) {
						// TODO: handle exception
						field.set(usuario, new ArrayList<>());
					}
				}

			}
			addInfo.put("usuario", usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}

}
