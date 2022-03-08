/**
 * 
 */
package com.atualged.exceptionhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.NonUniqueResultException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.atualged.exception.AtualGedException;
import com.atualged.exception.UsuariaInexistenteOuInativoException;


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
		if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
			return handleConstraintViolationException((org.hibernate.exception.ConstraintViolationException) ex.getCause(), request);
		}
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

	@ExceptionHandler({ AtualGedException.class })
	public ResponseEntity<Object> handleSigObraException(AtualGedException ex, WebRequest request) {
		String mu = ex.getMsgUsuario();
		String md = ExceptionUtils.getRootCauseMessage(ex);// ex.getMsgPadrao();
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ InvalidGrantException.class })
	public ResponseEntity<Object> handleInvalidGrantException(InvalidGrantException ex, WebRequest request) {
		String mu = "Usuário inexistente";
		String md = ExceptionUtils.getRootCauseMessage(ex); // ExceptionUtil.getRootCause
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ UsernameNotFoundException.class })
	public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
		String mu = "Usuário inexistente";
		String md = ExceptionUtils.getRootCauseMessage(ex); // ExceptionUtil.getRootCause
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ NullPointerException.class })
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
		String mu = "Valor nulo";
		String md = ExceptionUtils.getRootCauseMessage(ex); // ExceptionUtil.getRootCause
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ OAuth2Exception.class })
	public ResponseEntity<Object> handleNullPointerException(OAuth2Exception ex, WebRequest request) {
		String mu = "Acesso Negado";
		String md = ExceptionUtils.getRootCauseMessage(ex); // ExceptionUtil.getRootCause
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	@ExceptionHandler(value = AuthenticationException.class)
	public ResponseEntity<Object> handleAuthenticationExceptions(AuthenticationException ex, WebRequest response) {

		String mu = "Acesso Negado";
		String md = ExceptionUtils.getRootCauseMessage(ex); // ExceptionUtil.getRootCause
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, response);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		StringBuilder add = new StringBuilder("");
		try {
			Set<ConstraintViolation<?>> cv = ex.getConstraintViolations();

			for (ConstraintViolation<?> constraintViolation : cv) {
				if (constraintViolation instanceof ConstraintViolationImpl) {
					ConstraintViolationImpl<?> ci = (ConstraintViolationImpl<?>) constraintViolation;
					add.append("\n" + constraintViolation.getPropertyPath() + " : " +ci.getMessage() );
				} else {
				add.append(
						"\n" + constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessageTemplate());
				}
			}
		} finally {
			// TODO: handle finally clause
		}
		String mu = "\nErro de integridade " + add.toString();
		String md = ExceptionUtils.getRootCauseMessage(ex); // ExceptionUtil.getRootCause
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ org.hibernate.exception.ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolationException(org.hibernate.exception.ConstraintViolationException ex,
			WebRequest request) {
		String msg = ExceptionUtils.getRootCauseMessage(ex);
		if (msg.contains("already exists.")) {
			msg = acharField(ExceptionUtils.getRootCauseMessage(ex)) + " " + acharValor(ExceptionUtils.getRootCauseMessage(ex)) + " já existe"  ;
		}
		String mu = "\nErro de integridade \n" + msg;
		String md = ExceptionUtils.getRootCauseMessage(ex); // ExceptionUtil.getRootCause
		return handleExceptionInternal(ex, new Erro(mu, md), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ RollbackException.class })
	public ResponseEntity<Object> handleRollbackException(RollbackException ex, WebRequest request) {
		if (ex.getCause() instanceof ConstraintViolationException) {
			return handleConstraintViolationException((ConstraintViolationException) ex.getCause(), request);
		}
		String mu = "Erro de integridade do banco de dados";
		String md = ExceptionUtils.getRootCauseMessage(ex); // ExceptionUtil.getRootCause
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
	
	public String acharField(String value) {
		//return value 
		value = value.substring(value.indexOf("Key (") + "Key (".length());
		value = value.substring(0, value.indexOf(")"));
		return value;
	}
	
	public String acharValor(String value) {
		//return value 
		value = value.substring(value.indexOf("=(") + "=(".length());
		value = value.substring(0, value.indexOf(")"));
		return value;
	}
}
