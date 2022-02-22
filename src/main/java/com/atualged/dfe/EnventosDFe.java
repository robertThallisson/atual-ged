package com.atualged.dfe;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum;
import br.com.swconsultoria.nfe.dom.enuns.ManifestacaoEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt.LoteDistDFeInt.DocZip;
import br.com.swconsultoria.nfe.util.ManifestacaoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

public class EnventosDFe {

	public  List<String> download(ConfiguracoesNfe config) {
		List<String> list = new ArrayList<>();
		try {

			// Informe o CNPJ Do Destinatario (Deve ser o Mesmo do Certificado)
			String cnpj = config.getCertificado().getCnpjCpf();

			RetDistDFeInt retorno;
			String nsu = "000000000000000";
			retorno = Nfe.distribuicaoDfe(config, PessoaEnum.JURIDICA, cnpj, ConsultaDFeEnum.NSU, nsu);
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
						String arquivo = XmlNfeUtil.gZipToXml(docZip.getValue());
						list.add(arquivo);
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
		return list;
	}
	
	public void manifestar(ConfiguracoesNfe config,String Chave) {
		try {
			// Agora o evento pode aceitar uma lista de Manifestções para envio em Lote.
			// Para isso Foi criado o Objeto Manifestada
			Evento manifesta = new Evento();
			// Informe a chave da Nota a ser Manifestada
			manifesta.setChave(Chave);
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

		} catch (

		Exception e) {
			System.err.println();
			System.err.println("# Erro: " + e.getMessage());
		}
	}
}
