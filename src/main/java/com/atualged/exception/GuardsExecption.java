/**
 * 
 */
package com.atualged.exception;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NonUniqueResultException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author rober
 *
 */
@ControllerAdvice
public class GuardsExecption extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource ms;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String mu = ms.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String md = ex.getCause().toString();
		return handleExceptionInternal(ex, new Erro(mu, md), headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		List<Erro> list = criarListaDeErro(ex.getBindingResult());

		return handleExceptionInternal(ex, list, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String mu = ms.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String md = ex.toString();
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ NonUniqueResultException.class })
	public ResponseEntity<Object> handleNonUniqueResultException(NonUniqueResultException ex, WebRequest request) {
		String mu = ms.getMessage("recurso.varios-resultados", null, LocaleContextHolder.getLocale());
		String md = ex.toString();
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ IncorrectResultSizeDataAccessException.class })
	public ResponseEntity<Object> handleIncorrectResultSizeDataAccessException(
			IncorrectResultSizeDataAccessException ex, WebRequest request) {
		String mu = ms.getMessage("recurso.varios-resultados", null, LocaleContextHolder.getLocale());
		String md = ex.toString();
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		String mu = ms.getMessage("recurso.nao-permitido", null, LocaleContextHolder.getLocale());
		String md = ExceptionUtils.getRootCauseMessage(ex); // ExceptionUtil.getRootCause
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ UsuariaInexistenteOuInativoException.class })
	public ResponseEntity<Object> handleUsuariaInexistenteOuInativoException(UsuariaInexistenteOuInativoException ex,
			WebRequest request) {
		String mu = ms.getMessage("usuario.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String md = ex.toString();
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	private List<Erro> criarListaDeErro(BindingResult br) {
		List<Erro> list = new ArrayList<>();

		for (FieldError fe : br.getFieldErrors()) {
			String mu = ms.getMessage(fe, LocaleContextHolder.getLocale());
			String md = fe.toString();
			list.add(new Erro(mu, md));
		}

		return list;
	}

	public static class Erro {
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;

		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			super();
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}

		public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

	}
}
