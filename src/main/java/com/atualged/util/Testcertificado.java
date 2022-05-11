package com.atualged.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.ManifestacaoEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt.LoteDistDFeInt.DocZip;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.com.swconsultoria.nfe.util.ManifestacaoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;



public class Testcertificado {
	public static void main(String[] args) throws NfeException {
		try {
			Certificado certificado = certificadoArquivo();// certificadoCnpjCpf();
		//	certificado.setSenha("1234");
			certificado.setAtivarProperties(true);
			System.out.println(certificado.getArquivoBytes());
			System.out.println("Alias Certificado :" + certificado.getNome());
			System.out.println("Dias Restantes Certificado :" + certificado.getDiasRestantes());
			System.out.println("Validade Certificado :" + certificado.getVencimento());
			System.out.println("Validade Certificado :" + certificado.getSenha());
			System.out.println("Validade Certificado :" + certificado.getCnpjCpf());
			System.out.println("Validade Certificado :" + certificado.getNome());
			System.out.println("Validade Certificado :" + certificado.getSslProtocol());
			
			// certificado.setSenha("1234");
			// PARA REGISTRAR O CERTIFICADO NA SESSAO, FAÇA SOMENTE EM PROJETOS EXTERNO
			// JAVA NFE, CTE E OUTRAS APIS MINHAS JA CONTEM ESTA INICIALIZAÇÃO
			// CertificadoService.inicializaCertificado(certificado, new FileInputStream(new
			// File("C:\\Users\\rober\\OneDrive\\Área de Trabalho\\JBA COMERCIO DE PRODUTOS
			// ALIMENTICIOS LTDA24600833000151SENHA1234.pfx")));
			try {
				// Inicia As Configurações - ver
				// https://github.com/Samuel-Oliveira/Java_NFe/wiki/1-:-Configuracoes
				ConfiguracoesNfe config = iniciaConfigurações(certificado);

				// Efetua Consulta
				TRetConsStatServ retorno = Nfe.statusServico(config, DocumentoEnum.NFE);

				// Resultado
				System.out.println();
				Scanner s = new Scanner(System.in);
				s.nextLine();
				System.out.println("# Status: " + retorno.getCStat() + " - " + retorno.getXMotivo());
				manis(config);
				//manifestar(config);
				
			} catch (Exception e) {

				System.err.println("# Erro: " + e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static ConfiguracoesNfe iniciaConfigurações(Certificado certificado)
			throws NfeException, CertificadoException {
		// Certificado certificado =
		// https://github.com/Samuel-Oliveira/Java_Certificado/wiki

		return ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO, certificado, "C:\\Users\\rober\\Documents\\GitHub\\AtualGed\\src\\main\\java\\com\\atualged\\util\\schemas");
	}


	public static void manifestar(ConfiguracoesNfe config) {
		try {

			// Informe o CNPJ Do Destinatario (Deve ser o Mesmo do Certificado)
			String cnpj = config.getCertificado().getCnpjCpf();

			RetDistDFeInt retorno;

			// Para Consulta Via CHAVE
//	                String chave = "35170843283811001202550010046314601229130549";
//	                retorno = Nfe.distribuicaoDfe(PessoaEnum.JURIDICA, cnpj, ConsultaDFeEnum.CHAVE, chave);

			// Para Consulta Via NSU

			String nsu = "000000000000000";
			retorno = Nfe.distribuicaoDfe(config, PessoaEnum.JURIDICA, cnpj, ConsultaDFeEnum.NSU, nsu);
			new Scanner(System.in).nextLine();
			if (StatusEnum.DOC_LOCALIZADO_PARA_DESTINATARIO.getCodigo().equals(retorno.getCStat())) {
				System.out.println();
				System.out.println("# Status: " + retorno.getCStat() + " - " + retorno.getXMotivo());
				System.out.println("# NSU Atual: " + retorno.getUltNSU());
				System.out.println("# Max NSU: " + retorno.getMaxNSU());
				System.out.println("# Max NSU: " + retorno.getMaxNSU());

				// Aqui Recebe a Lista De XML (No Maximo 50 por Consulta)
				List<DocZip> listaDoc = retorno.getLoteDistDFeInt().getDocZip();
				for (DocZip docZip : listaDoc) {
					System.out.println();
					System.out.println("# Schema: " + docZip.getSchema());
					switch (docZip.getSchema()) {
					case "resNFe_v1.01.xsd":
						System.out.println(
								"# Este é o XML em resumo, deve ser feito a Manifestação para o Objeter o XML Completo.");
						break;
					case "procNFe_v4.00.xsd":
						System.out.println("# XML Completo.");
						break;
					case "procEventoNFe_v1.00.xsd":
						System.out.println("# XML Evento.");
						break;
					}
					// Transforma o GZip em XML
					String xml = XmlNfeUtil.gZipToXml(docZip.getValue());
					System.out.println("# XML: " + xml);
				}
			} else {
				System.out.println();
				System.out.println("# Status: " + retorno.getCStat() + " - " + retorno.getXMotivo());
			}
		} catch (Exception e) {
			System.err.println();
			System.err.println("# Erro: " + e.getMessage());
		}
	}

	private static void manis(ConfiguracoesNfe config) {
		try {
			// Agora o evento pode aceitar uma lista de Manifestções para envio em Lote.
			// Para isso Foi criado o Objeto Manifestada
			Evento manifesta = new Evento();
			// Informe a chave da Nota a ser Manifestada
			manifesta.setChave("52190626995215000183550020000013431000007600");
			// Informe o CNPJ do emitente
			manifesta.setCnpj(config.getCertificado().getCnpjCpf());
			// Caso o Tipo de manifestação seja OPERAÇÂO Não REALIZADA, Informe o Motivo do
			// Manifestacao
			manifesta.setMotivo("DESCONHECIMENTO_DA_OPERACAO");
			// Informe a data do Manifestacao
			manifesta.setDataEvento(LocalDateTime.now());
			// Informe o Tipo da Manifestação
			manifesta.setTipoManifestacao(ManifestacaoEnum.DESCONHECIMENTO_DA_OPERACAO);
			// Monta o Evento de Manifestação
			TEnvEvento enviEvento = ManifestacaoUtil.montaManifestacao(manifesta, config);
			// manifesta.set
			// Envia o Evento de Manifestação
			TRetEnvEvento retorno = Nfe.manifestacao(config, enviEvento, true);

			// Valida o Retorno do Cancelamento
			RetornoUtil.validaManifestacao(retorno);

			// Resultado
			System.out.println();
			retorno.getRetEvento().forEach(resultado -> {
				System.out.println("# Chave: " + resultado.getInfEvento().getChNFe());
				System.out.println("# Status: " + resultado.getInfEvento().getCStat() + " - "
						+ resultado.getInfEvento().getXMotivo());
				System.out.println("# Protocolo: " + resultado.getInfEvento().getNProt());
			});

			// Cria ProcEvento de Manifestacao
			String proc = ManifestacaoUtil.criaProcEventoManifestacao(config, enviEvento,
					retorno.getRetEvento().get(0));
			System.out.println();
			System.out.println("# ProcEvento : " + proc);

		} catch (Exception e) {
			System.err.println();
			System.err.println("# Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void download(ConfiguracoesNfe config) throws IOException, NfeException {
		 //Informe o CNPJ Do Destinatario (Deve ser o Mesmo do Certificado)
        String cnpj = "XXX";

        RetDistDFeInt retorno;

        //Para Consulta Via CHAVE
//            String chave = "35170843283811001202550010046314601229130549";
//            retorno = Nfe.distribuicaoDfe(PessoaEnum.JURIDICA, cnpj, ConsultaDFeEnum.CHAVE, chave);

        //Para Consulta Via NSU
        String nsu = "000000000000000";
        retorno = Nfe.distribuicaoDfe(config, PessoaEnum.JURIDICA, cnpj, ConsultaDFeEnum.NSU, nsu);

        if (StatusEnum.DOC_LOCALIZADO_PARA_DESTINATARIO.getCodigo().equals(retorno.getCStat())) {
            System.out.println();
            System.out.println("# Status: " + retorno.getCStat() + " - " + retorno.getXMotivo());
            System.out.println("# NSU Atual: " + retorno.getUltNSU());
            System.out.println("# Max NSU: " + retorno.getMaxNSU());
            System.out.println("# Max NSU: " + retorno.getMaxNSU());

            //Aqui Recebe a Lista De XML (No Maximo 50 por Consulta)
            List<DocZip> listaDoc = retorno.getLoteDistDFeInt().getDocZip();
            for (DocZip docZip : listaDoc) {
                System.out.println();
                System.out.println("# Schema: " + docZip.getSchema());
                switch (docZip.getSchema()) {
                    case "resNFe_v1.01.xsd":
                        System.out.println("# Este é o XML em resumo, deve ser feito a Manifestação para o Objeter o XML Completo.");
                        break;
                    case "procNFe_v4.00.xsd":
                        System.out.println("# XML Completo.");
                        break;
                    case "procEventoNFe_v1.00.xsd":
                        System.out.println("# XML Evento.");
                        break;
                }
                //Transforma o GZip em XML
                String xml = XmlNfeUtil.gZipToXml(docZip.getValue());
                System.out.println("# XML: " + xml);
            }
        } else {
            System.out.println();
            System.out.println("# Status: " + retorno.getCStat() + " - " + retorno.getXMotivo());
        }
	}
	

	private static Certificado certificadoCnpjCpf() throws CertificadoException {
		String cnpj = "37878915000104";
		return CertificadoService.getCertificadoByCnpjCpf(cnpj);

	}
	private static Certificado certificadoArquivo() throws Exception {
		String caminhoCertificado = "D:\\empresa\\DIVINO TAVARES ME.pfx";
        String senha = "1234";

        return CertificadoService.certificadoPfx(caminhoCertificado, senha);

	}

	private static Certificado certifidoA1Pfx() throws Exception{ 
		File f = new File("D:/senha 1234.pfx"); 
		FileInputStream fs = new FileInputStream(f); 
		byte[] byt = new byte[(int)f.length()];
		for(int i = 0;i < f.length();i++){ byt[i] = (byte) fs.read(); 
		}   
		Certificado certificado = CertificadoService.certificadoPfxBytes(byt, "1234");  
		return certificado;
	}
}
