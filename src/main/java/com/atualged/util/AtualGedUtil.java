package com.atualged.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.StreamUtils;

import com.atualged.exception.AtualGedException;
import com.atualged.model.Empresa;
import com.atualged.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AtualGedUtil {
	private static PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	// recebe seha não encodada e compara com senha encodada
	public static boolean senhasIguais(String senha, String senhaEncodada) {
		return encoder.matches(senha, senhaEncodada);
	}

	/* decofica o token e retorna a propriedade passada em Json*/
	public static Object decodeTokenRequest(String propiedade) {
		OAuth2AuthenticationDetails teste = (OAuth2AuthenticationDetails) ((SecurityContext) SecurityContextHolder
				.getContext()).getAuthentication().getDetails();

		Jwt decode = JwtHelper.decode(teste.getTokenValue());
		try {
			
			JSONObject jb = new JSONObject(decode.getClaims().toString());
			return jb.getJSONObject(propiedade);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw new AtualGedException("falhar ao obter informações do usuario logado ", e.getMessage());
		}
		
	}

	public static Usuario getUsuarioTokenRequest() {

		try {
			return new ObjectMapper().readValue(decodeTokenRequest("usuario").toString(), Usuario.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AtualGedException();
		}
		
	}

	public static Empresa getEmpresaTokenRequest() {
		 try {
			return new ObjectMapper().readValue(decodeTokenRequest("empresa").toString(), Empresa.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AtualGedException();
		}
		
	}
	
	
	public static String logoSigObra(InputStream in) throws IOException {
		
		byte[] encodedImage = Base64.encodeBase64(StreamUtils.copyToByteArray(in));
		String encodeImg = new String(encodedImage);
		return encodeImg;
	}
	
	

}
