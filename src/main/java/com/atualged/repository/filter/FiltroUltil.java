package com.atualged.repository.filter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.atualged.model.annotations.IgnorarField;

public class FiltroUltil {
	public static List<String> getFiltroForClass(Object object) {
		List<String> list = new ArrayList<>();
		for (Field field : object.getClass().getDeclaredFields()) {
			if (field.getDeclaredAnnotation(IgnorarField.class) == null) {
				list.add(field.getName());
			}
		}
		return list;
	}
}
