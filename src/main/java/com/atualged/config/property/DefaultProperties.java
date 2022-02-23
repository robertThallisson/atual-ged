package com.atualged.config.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class DefaultProperties {

	private final static String PROPERTIES_FILENAME = "default.properties";
	final Properties userProperties = new Properties();
	File file;

	public DefaultProperties() throws IOException {
		// TODO Auto-generated constructor stub
		userProperties();
	}

	Properties userProperties() throws IOException {
		file = null;
		try {
			file = new File("default.properties");

			if (!file.exists()) {
				file.createNewFile();
			}
			System.out.println(file.getAbsolutePath());

		} catch (Exception e) {
			System.out.print("Erro ao criar arquivo de propriedades");
			file = new File(PROPERTIES_FILENAME);
		}

		userProperties.load(new FileInputStream(file));
		System.out.println(userProperties.getProperty("bd"));
		System.out.println(userProperties.getProperty("backup"));
		return userProperties;
	}

	public void gravar() {
		try {
			userProperties.store(new FileOutputStream(file), "");
		//	userProperties.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Properties getUserProperties() {
		return userProperties;
	}
}
