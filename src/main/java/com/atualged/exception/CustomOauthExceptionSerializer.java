package com.atualged.exception;

import java.io.IOException;
import java.util.Map.Entry;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomOauthExceptionSerializer() {
		super(CustomOauthException.class);
	}

	@Override
	public void serialize(CustomOauthException value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException {
		// TODO Auto-generated method stub
		/*
		 * jgen.writeStartObject(); jgen.writeStringField("error",
		 * value.getOAuth2ErrorCode()); jgen.writeStringField("error_description",
		 * value.getMessage()); jgen.writeStringField("mensagemUsuario",
		 * value.getMensagemUsuario()); jgen.writeNumberField("status", -5);
		 * jgen.writeFieldName("errors"); jgen.writeEndObject();
		 */
		jgen.writeStartObject();
		jgen.writeStringField("error", value.getOAuth2ErrorCode());
		String errorMessage = value.getMessage();
		if (errorMessage != null) {
			errorMessage = HtmlUtils.htmlEscape(errorMessage);
		}
		jgen.writeStringField("error_description", errorMessage);
		jgen.writeStringField("mensagemUsuario", value.getMensagemUsuario());
		if (value.getAdditionalInformation() != null) {
			for (Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
				String key = entry.getKey();
				String add = entry.getValue();
				jgen.writeStringField(key, add);
			}
		}
		jgen.writeEndObject();
	}

}
