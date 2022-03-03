package com.atualged.repository.query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atualged.repository.filter.Filtro;
import com.atualged.model.Usuario;

public class UsuarioRepositoryImpl extends RepositoryImpl<Usuario> {
	
	public UsuarioRepositoryImpl() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	public List<Usuario> filtrar(Filtro filtro) {
		// TODO Auto-generated method stub
		return super.filtrar(filtro);
	}
	@Override
	public Page<Usuario> filtrar(Filtro filtro, Pageable pageable) {
		// TODO Auto-generated method stub
		return super.filtrar(filtro, pageable);
	}
}
