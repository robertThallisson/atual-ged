package com.atualged.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.atualged.event.RecursoEvent;

public class RescursoListener implements ApplicationListener<RecursoEvent> {

	@Override
	public void onApplicationEvent(RecursoEvent event) {
		// TODO Auto-generated method stub
		HttpServletResponse respose = event.getResponse();
		AdicionarHeader(respose);
	}

	private void AdicionarHeader(HttpServletResponse respose) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
		respose.setHeader("Location",uri.toASCIIString());
	}

}
