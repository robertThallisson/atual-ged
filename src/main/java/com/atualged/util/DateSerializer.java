package com.atualged.util;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualSerializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DateSerializer extends JsonSerializer<LocalDate>
		implements  ContextualSerializer<LocalDate> {
	private final String format;

    private DateSerializer(final String format) {
        this.format = format;
    }

    public DateSerializer() {
        this.format = null;
    }

    @Override
    public void serialize(final LocalDate value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException {
        jgen.writeString(new SimpleDateFormat(format).format(value));
    }

    @Override
    public JsonSerializer<LocalDate> createContextual(final SerializationConfig serializationConfig, final BeanProperty beanProperty) throws JsonMappingException {
        final AnnotatedElement annotated = beanProperty.getMember().getAnnotated();
        return new DateSerializer(( annotated.getAnnotation(JsonFormat.class)).toString());
    }
}
