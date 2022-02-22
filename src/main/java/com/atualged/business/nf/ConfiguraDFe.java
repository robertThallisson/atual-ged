package com.atualged.business.nf;


import com.atualged.model.Empresa;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;

public class ConfiguraDFe {
	public static ConfiguracoesNfe iniciaConfigurações(Empresa empresa, Certificado certificado) throws Exception {
		// Certificado certificado =
		// https://github.com/Samuel-Oliveira/Java_Certificado/wiki

		return ConfiguracoesNfe.criarConfiguracoes(getEnumEstado(empresa), AmbienteEnum.HOMOLOGACAO, certificado,
				"/schemas");
	}
	
	public static ConfiguracoesNfe iniciaConfigurações(Empresa empresa, com.atualged.model.Certificado certificado) throws Exception {
		// Certificado certificado =
		// https://github.com/Samuel-Oliveira/Java_Certificado/wiki
		System.out.println(ConfiguraDFe.class.getResource("/schemas/").getPath());
		return ConfiguracoesNfe.criarConfiguracoes(getEnumEstado(empresa), AmbienteEnum.HOMOLOGACAO, certifidoA1Pfx(certificado),
				ConfiguraDFe.class.getResource("/schemas/").getPath());
	}

	public static Certificado certificadoCnpjCpf(String CNPJ) throws CertificadoException {

		String cnpj = "11172182000102";
		return CertificadoService.getCertificadoByCnpjCpf(cnpj);

	}
	

	public static Certificado certifidoA1Pfx(com.atualged.model.Certificado certificado) throws Exception {
		byte[] certificadoByte;

		certificadoByte = certificado.getArquivo();
		String senha = certificado.getSenha();
		Certificado a = CertificadoService.certificadoPfxBytes(certificadoByte, senha);
		System.out.println(a);
		return a;
	}

	public static EstadosEnum getEnumEstado(Empresa empresa) {
		switch (empresa.getUf().toUpperCase()) {
		case "GO": {
			return EstadosEnum.GO;
		}
		case "RO": {
			return EstadosEnum.RO;
		}
		case "AC": {
			return EstadosEnum.AC;
		}
		case "AM": {
			return EstadosEnum.AM;
		}
		case "RR": {
			return EstadosEnum.RR;
		}
		case "PA": {
			return EstadosEnum.PA;
		}
		case "AP": {
			return EstadosEnum.AP;
		}
		case "TO": {
			return EstadosEnum.TO;
		}
		case "MA": {
			return EstadosEnum.MA;
		}
		case "PI": {
			return EstadosEnum.PI;
		}
		case "CE": {
			return EstadosEnum.CE;
		}
		case "RN": {
			return EstadosEnum.RN;
		}
		case "PB": {
			return EstadosEnum.PB;
		}
		case "PE": {
			return EstadosEnum.PE;
		}
		case "AL": {
			return EstadosEnum.AL;
		}
		case "SE": {
			return EstadosEnum.SE;
		}
		case "BA": {
			return EstadosEnum.BA;
		}
		case "MG": {
			return EstadosEnum.MG;
		}
		case "RJ": {
			return EstadosEnum.RJ;
		}
		case "SP": {
			return EstadosEnum.SP;
		}
		case "PR": {
			return EstadosEnum.PR;
		}
		case "SC": {
			return EstadosEnum.SC;
		}
		case "RS": {
			return EstadosEnum.RS;
		}
		case "MS": {
			return EstadosEnum.MS;
		}
		case "MT": {
			return EstadosEnum.MT;
		}
		case "DF": {
			return EstadosEnum.DF;
		}

		default: {
			return EstadosEnum.GO;
		}
		}
	}
}
