package com.atualged.repository.query;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.atualged.repository.filter.Filtro;
import com.atualged.repository.filter.Pesquisa;

@Repository
@Transactional(readOnly = true)
public class RepositoryImpl<T> implements RepositoryQuery<T> {
	@PersistenceContext
	EntityManager em;
	private Class<T> persistentClass;
	private String className;

	public RepositoryImpl() {
		// TODO Auto-generated constructor stub

		try {

			@SuppressWarnings("unchecked")
			Class<T> persistentClass2 = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];

			ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
			String s = ((ParameterizedType) superclass).getActualTypeArguments()[0].toString();
			int inicio = s.lastIndexOf(".") + 1;
			int fim = s.length();
			className = s.substring(inicio, fim);

			setPersistentClass(persistentClass2);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public List<T> filtar(Filtro filtro) {
		System.out.println("****************filtrou ");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(persistentClass);
		Root<T> root = cq.from(persistentClass);

		Predicate[] predicate = criarRestricoes(filtro, builder, root);
		cq.where(predicate);
		TypedQuery<T> query = em.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public Page<T> filtar(Filtro filtro, Pageable pageable) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(persistentClass);
		Root<T> root = cq.from(persistentClass);

		Predicate[] predicate = criarRestricoes(filtro, builder, root);
		cq.where(predicate);
		TypedQuery<T> query = em.createQuery(cq);

		adicionarPaginaca(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(filtro));
	}

	private long total(Filtro filtro) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = builder.createQuery(Long.class);
		Root<T> root = cq.from(getPersistentClass());

		Predicate[] predicate = criarRestricoes(filtro, builder, root);
		cq.where(predicate);
		cq.select(builder.count(root));
		return em.createQuery(cq).getSingleResult();
	}

	private void adicionarPaginaca(TypedQuery<T> query, Pageable pageable) {
		// TODO Auto-generated method stub
		int paginaAtual = pageable.getPageNumber();
		int totalRegitroPorPagina = pageable.getPageSize();
		int primerioRegistoDaPagina = paginaAtual * totalRegitroPorPagina;
		query.setFirstResult(primerioRegistoDaPagina);
		query.setMaxResults(totalRegitroPorPagina);
	}

	private Predicate[] criarRestricoes(Filtro filtro, CriteriaBuilder builder, Root<T> root) {
		// TODO Auto-generated method stub
		List<Predicate> list = new ArrayList<>();
		if (filtro != null && filtro.getPesquisas() != null) {
			for (Pesquisa pesquisa : filtro.getPesquisas()) {
				if (!StringUtils.isEmpty(pesquisa.getNome()) && !StringUtils.isEmpty(pesquisa.getComparacao())
						&& !StringUtils.isEmpty(pesquisa.getValor())) {
					list.add(novoPredicate(pesquisa, builder, root));
				}
			}
		}
		return list.toArray(new Predicate[list.size()]);
	}

	private Predicate novoPredicate(Pesquisa pesquisa, CriteriaBuilder builder, Root<T> root) {
		// TODO Auto-generated method stub

		if (pesquisa.getComparacao().equals("=")) {
			return builder.equal(root.get("id"), Long.valueOf(1));
		}

		if (pesquisa.getComparacao().equals("like")) {
			return builder.like(root.get(pesquisa.getNome()), pesquisa.getValor());
		}
		if (pesquisa.getComparacao().equals(">")) {
			return builder.gt(root.get(pesquisa.getNome()), Double.parseDouble(pesquisa.getValor()));
		}
		if (pesquisa.getComparacao().equals(">=")) {
			System.out.println(Double.parseDouble(pesquisa.getValor()));
			System.out.println(
					builder.ge(root.get(pesquisa.getNome()), Double.parseDouble(pesquisa.getValor())).toString());
			return builder.ge(root.get(pesquisa.getNome()), Double.parseDouble(pesquisa.getValor()));
		}
		if (pesquisa.getComparacao().equals("<")) {
			return builder.lt(root.get(pesquisa.getNome()), Double.parseDouble(pesquisa.getValor()));
		}
		if (pesquisa.getComparacao().equals("<=")) {
			return builder.le(root.get(pesquisa.getNome()), Double.parseDouble(pesquisa.getValor()));
		}
//		if (pesquisa.getComparacao().equals("=")) {
//			return builder.like(builder.lower(root.get(pesquisa.getNome())), "%"+pesquisa.getValor()+"%");
//		}
		return null;

	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
