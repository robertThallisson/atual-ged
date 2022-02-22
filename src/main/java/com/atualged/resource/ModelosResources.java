package com.atualged.resource;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atualged.repository.filter.Filtro;
import com.atualged.repository.filter.Pesquisa;

import tools.devnull.trugger.scan.ClassScan;

@RestController
@RequestMapping("/modelos")
public class ModelosResources {
	@SuppressWarnings("rawtypes")
	@GetMapping(produces = "application/json")
	public ResponseEntity<?> getAll() {
		List<Object> list = new ArrayList<>();
		// Set<Class> classes = Clas //
		// ClassScan.scan().assignableTo(Serializable.class).in("com.guardsman.model");

		// Class ClassesSelector

		Set<Class> classes = ClassScan.findClasses().assignableTo(Serializable.class).in("com.atualged.model");
		for (Class ob : classes) {
			try {
				int inicio = ob.getName().lastIndexOf(".") + 1;
				int fim = ob.getName().length();
				String className = ob.getName().substring(inicio, fim);
				Object object = ob.newInstance();
			
				for (Field field : object.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if (field.getType() == List.class || field.getType() == ArrayList.class) {
						field.set(object,new ArrayList<>());// field.getType().newInstance();
					}
					
				}
				list.add(new Modelo(className, object));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		list.add(new Modelo("Pesquisa", new Pesquisa()));
		list.add(new Modelo("Filtro", new Filtro()));
		return !list.isEmpty() ? ResponseEntity.ok(list) : ResponseEntity.noContent().build();
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/{nome}", produces = "application/json")
	public ResponseEntity<?> getByNome(@PathVariable String nome) {
		Object obj = null;
		
		Set<Class> classes = ClassScan.findClasses().assignableTo(Serializable.class).in("com.guardsman.model");
		for (Class ob : classes) {
			try {
				int inicio = ob.getName().lastIndexOf(".") + 1;
				int fim = ob.getName().length();
				String className = ob.getName().substring(inicio, fim);
				Object object = ob.newInstance();
				if (className.toLowerCase().equals(nome.toLowerCase())) {
					for (Field field : object.getClass().getDeclaredFields()) {
						if (field.getType() == List.class || field.getType() == ArrayList.class) {
							field.setAccessible(true);
							field.set(object,new ArrayList<>());
						}
					}
					
					obj = new Modelo(className, object);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.noContent().build();
	}

	@SuppressWarnings("unused")
	private class Modelo {
		private String nome;
		private Object object;

		public Modelo(String nome, Object object) {
			super();
			this.nome = nome;
			this.object = object;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Object getObject() {
			return object;
		}

		public void setObject(Object object) {
			this.object = object;
		}

	}
}
