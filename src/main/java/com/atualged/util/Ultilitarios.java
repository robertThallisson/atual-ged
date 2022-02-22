package com.atualged.util;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.atualged.model.Estado;

public class Ultilitarios {

	public static void main(String[] args) {



		float pi = 25252.856f;
		String str = String.format("%.02f", pi);
		System.out.println("formatted float up to 2 decimals " + str);

		toTypeScript();
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		System.out.println(encoder.encode("dgp9f2dryr"));

	}

	public static void toTypeScript() {

		Field fields[] = Estado.class.getDeclaredFields();

		String name = Estado.class.getSimpleName();
		String script = "CREATE TABLE if not exists " + tratarNomeSql(name) + " ( \n";
		String constrant = "";
		for (Field field : fields) {
			if (!field.getName().equals("serialVersionUID")) {
				String tipo = "";
				if (field.getType() == List.class || field.getType() == ArrayList.class) {
					tipo = ": Array<" + field.getType().getSimpleName() + ">;";
				}
				if ((field.getType() == String.class)) {
					tipo = ": string;";// field.getType().newInstance();

					script += "  " + tratarNomeSql(field.getName()) + " character varying     ,\n";
				}

				if ((field.getType() == Boolean.class)) {
					tipo = ": boolean;";// field.getType().newInstance();

					script += "  " + tratarNomeSql(field.getName()) + " boolean     ,\n";
				}
				if ((field.getType() == Long.class) || (field.getType() == Integer.class)
						|| (field.getType() == Float.class) || (field.getType() == Double.class)) {
					tipo = ": number;";// field.getType().newInstance();
					if (field.getName().equals("id")) {
						script += " id bigserial    ,\n";

						constrant += " CONSTRAINT pk_" + tratarNomeSql(name) + " PRIMARY KEY (id) ";
					} else {
						if ((field.getType() == Float.class) || (field.getType() == Double.class)) {
							script += "  " + tratarNomeSql(field.getName()) + " numeric(15, 2)      ,\n";
						} else {
							script += "  " + tratarNomeSql(field.getName()) + " bigint    ,\n";
						}

					}

				}

				if ((field.getType() == LocalDate.class) || (field.getType() == LocalDateTime.class)) {
					tipo = ": any;";
					if ((field.getType() == LocalDate.class)) {
						script += "  " + tratarNomeSql(field.getName()) + "  date     ,\n";
					} else {
						script += "  " + tratarNomeSql(field.getName()) + " timestamp without time zone    ,\n";
					}
				}

				if ((field.getType() == byte[].class)) {
					tipo = ": any;";
					script += "  " + tratarNomeSql(field.getName()) + "  bytea     ,\n";
				}

				if (tipo.equals("")) {
					tipo = ": " + field.getType().getSimpleName() + ";";// getType().getClass().getName();

					String nomeField = tratarNomeSql(field.getType().getSimpleName());
					script += " " + nomeField + "_id  bigint    ,\n";

					if (!constrant.equals("")) {
						constrant += "\n,";
					}

					constrant += " CONSTRAINT fk_" + nomeField + " FOREIGN KEY (" + nomeField + "_id) REFERENCES "
							+ tratarNomeSql(field.getType().getSimpleName()) + "  (id)";
				}

				System.out.println(field.getName() + tipo);

			}

		}

		script += constrant + "   \n );";

		System.out.println(script);
	}

	public static String tratarNomeSql(String value) {
		if (value.equals(value.toUpperCase())) {
			return value.toLowerCase();
		}
		StringBuilder stringBuilder = new StringBuilder();
		boolean primeira = true;
		for (char letra : value.toCharArray()) {
			stringBuilder.append((Character.isUpperCase(letra) && !primeira ? "_" + Character.toLowerCase(letra)
					: Character.toLowerCase(letra)));
			primeira = false;
		}
		// System.out.println(stringBuilder.toString());
		return stringBuilder.toString();
	}

	public static void gerarPermissao(String nome) {
		System.out.println("INSERT INTO permissao\r\n" + "    (\r\n" + "    ativo, descricao, visivel)\r\n"
				+ "VALUES\r\n" + "    ( true, '" + nome + ":salvar', true);");
		System.out.println("INSERT INTO permissao\r\n" + "    (\r\n" + "    ativo, descricao, visivel)\r\n"
				+ "VALUES\r\n" + "    ( true, '" + nome + ":pesquisar', true);");
	}
}
