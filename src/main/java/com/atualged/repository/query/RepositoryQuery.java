package com.atualged.repository.query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.atualged.repository.filter.Filtro;

@NoRepositoryBean
public interface RepositoryQuery<T extends Object> {
	public List<T> filtar(Filtro filtro);
	public Page<T> filtar(Filtro filtro,Pageable pageable);
	
}
