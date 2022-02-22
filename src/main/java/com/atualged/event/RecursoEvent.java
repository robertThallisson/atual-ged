package com.atualged.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private Long Codigo;
	
	public RecursoEvent(Object source,HttpServletResponse response,Long Codigo) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getCodigo() {
		return Codigo;
	}

}
