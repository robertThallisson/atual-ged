package com.atualged.find;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atualged.config.dfe.ConfiguraDFe;
import com.atualged.model.Empresa;
import com.atualged.model.Nota;
import com.atualged.repository.CertificadoRepository;
import com.atualged.repository.EmpresaRepository;
import com.atualged.repository.NotaRepository;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ManifestacaoEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt.LoteDistDFeInt.DocZip;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.com.swconsultoria.nfe.util.ManifestacaoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

@Service
public class FindDFe {
	private ThreadPoolExecutor threadExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
	@Autowired
	private EmpresaRepository er;

	
	@Autowired
	private CertificadoRepository certificadoRepository;
	
	
	@Autowired
	private NotaRepository notaRepository;
	
	public void buscar() {

		new Thread(new Runnable() {
			LocalDate time;

			@Override
			public void run() {
				// TODO Auto-generated method stub


				if (time == null || time != LocalDate.now()) {
					time = LocalDate.now();
					if(threadExecutor != null) {
						threadExecutor.shutdown();
					}
					threadExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
					buscarEmpresas();
				}
			}

		}).start();
	}
	private void buscarEmpresas() {
		// TODO Auto-generated method stub
		List<Empresa> list = er.findAll();
		
		for (Empresa empresa : list) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// LocalDate date = null;
					try {
						/// System.out.println(aux.getAttributes());
						buscarDFe(empresa);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
			threadExecutor.execute(thread);
		}
	}
	
	private void buscarDFe(Empresa empresa) {
		com.atualged.model.Certificado cert = certificadoRepository.findByEmpresa(empresa);
		if (cert == null) {
			return;
		}
		
		try {
			Certificado certificado = ConfiguraDFe.certifidoA1Pfx(cert);
			ConfiguracoesNfe config = ConfiguraDFe.iniciaConfigurações(empresa, certificado);
			consultar(config);
			download(config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error ---------------->" +  e.getMessage());
			e.printStackTrace(); 
		}
		 
	}
	
	void consultar(ConfiguracoesNfe config) throws NfeException {
		TRetConsStatServ retorno = Nfe.statusServico(config, DocumentoEnum.NFE);
		System.out.println("# Status: " + retorno.getCStat() + " - " + retorno.getXMotivo());
	}
	
	
	public void manifesta(ConfiguracoesNfe config ) throws NfeException, JAXBException {


           //Agora o evento pode aceitar uma lista de Manifestções para envio em Lote.
           //Para isso Foi criado o Objeto Manifestada
           Evento manifesta = new Evento();
           //Informe a chave da Nota a ser Manifestada
           //manifesta.setChave("ZZZ");
           //Informe o CNPJ do emitente
           manifesta.setCnpj(config.getCertificado().getCnpjCpf());
           //Caso o Tipo de manifestação seja OPERAÇÂO Não REALIZADA, Informe o Motivo do Manifestacao
           manifesta.setMotivo("Teste Operação Não Realizada");
           //Informe a data do Manifestacao
           manifesta.setDataEvento(LocalDateTime.now());
           //Informe o Tipo da Manifestação
           manifesta.setTipoManifestacao(ManifestacaoEnum.DESCONHECIMENTO_DA_OPERACAO);
          // manifesta.setTipoManifestacao(null);
           
           //Monta o Evento de Manifestação
           TEnvEvento enviEvento = ManifestacaoUtil.montaManifestacao(manifesta, config);
        
           //Envia o Evento de Manifestação
           TRetEnvEvento retorno = Nfe.manifestacao(config, enviEvento, true);

           //Valida o Retorno do Cancelamento
           RetornoUtil.validaManifestacao(retorno);

           //Resultado
           System.out.println();
           retorno.getRetEvento().forEach( resultado -> {
               System.out.println("# Chave: " + resultado.getInfEvento().getChNFe());
               System.out.println("# Status: " + resultado.getInfEvento().getCStat() + " - " + resultado.getInfEvento().getXMotivo());
               System.out.println("# Protocolo: " + resultado.getInfEvento().getNProt());
           });

           //Cria ProcEvento de Manifestacao
           String proc = ManifestacaoUtil.criaProcEventoManifestacao(config, enviEvento, retorno.getRetEvento().get(0));
           System.out.println();
           System.out.println("# ProcEvento : " + proc);
	}
	
	
	public void download(ConfiguracoesNfe config) throws NfeException, IOException {
		 //Informe o CNPJ Do Destinatario (Deve ser o Mesmo do Certificado)
       String cnpj = config.getCertificado().getCnpjCpf();

       RetDistDFeInt retorno;

       //Para Consulta Via CHAVE
//           String chave = "35170843283811001202550010046314601229130549";
//           retorno = Nfe.distribuicaoDfe(PessoaEnum.JURIDICA, cnpj, ConsultaDFeEnum.CHAVE, chave);

       //Para Consulta Via NSU
       String nsu = "000000000000001";
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
               System.out.println("# Schema: " + docZip.getSchema() + " NSU: " + docZip.getNSU());
               
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
	
	private void  salvarNota(Empresa  empresa, String xml) throws JAXBException {
		
		TEnviNFe enviNFe = XmlNfeUtil.xmlToObject(xml, TEnviNFe.class);

        // Monta e Assina o XML
        //enviNFe = Nfe.montaNfe(config, enviNFe, true);
        
		Nota nota = new Nota();
		nota.setEmpresa(empresa);
		nota.setXml(xml);
	//	nota.setValorTotal(enviNFe.getNFe().get(0).getInfNFe().getTotal().getICMSTot().getVProd());
		
		notaRepository.save(nota);
	}
	
	
}

