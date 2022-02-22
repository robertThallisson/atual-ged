package com.atualged;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.atualged.config.property.AtualGedProperty;


@EnableConfigurationProperties(AtualGedProperty.class)
@SpringBootApplication
public class AtualGedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtualGedApplication.class, args);
	}
}
