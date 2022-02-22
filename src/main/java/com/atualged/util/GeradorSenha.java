package com.atualged.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GeradorSenha {
	public static void main(String[] args) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		System.out.println(encoder.matches("2", encoder.encode("1")));
		
		System.out.println(encoder.encode("1"));
	}
}
