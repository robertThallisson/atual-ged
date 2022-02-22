package com.atualged.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("atualged")
public class AtualGedProperty {
	
	private String origemPermitida = "*";
	private final Segurancao segurancao = new Segurancao();
	
	public Segurancao getSegurancao() {
		return segurancao;
	}

	public static class Segurancao{
		private boolean enablehttps;

		public boolean isEnablehttps() {
			return enablehttps;
		}

		public void setEnablehttps(boolean enablehttps) {
			this.enablehttps = enablehttps;
		}
	}

	public String getOrigemPermitida() {
		return origemPermitida;
	}

	public void setOrigemPermitida(String origemPermitida) {
		this.origemPermitida = origemPermitida;
	}
}
