package com.atualged.repository.query;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.atualged.repository.filter.Filtro;
import com.atualged.repository.filter.Pesquisa;
import com.atualged.util.CPFCNPJUtil;
import com.atualged.util.ValidadorDigitos;

@Repository
@Transactional(readOnly = true)
public class RepositoryImpl<T> implements RepositoryQuery<T> {
	@PersistenceContext
	EntityManager em;

	@Autowired
	JdbcTemplate jdbcTemplate;

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
	public List<T> filtrar(Filtro filtro) {
		System.out.println("****************filtrou ");

		if (filtro.isNativo()) {
			return filtrarNativo(filtro);
		}
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(persistentClass);
		Root<T> root = cq.from(persistentClass);

		Predicate[] predicate = criarRestricoes(filtro, builder, root);
		cq.where(predicate);
		TypedQuery<T> query = em.createQuery(cq);
		List<T> a = query.getResultList();
		return a;
	}

	@Override
	public Page<T> filtrar(Filtro filtro, Pageable pageable)  {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<T> filtrarNativo(Filtro filtro) {
		
		return em.createNativeQuery(getSql(filtro), persistentClass).getResultList();

	}

	protected String getSql(Filtro filtro) {
		// TODO Auto-generated method stub
		String sql = filtro.getSql().toLowerCase().trim().toLowerCase().replace(" ", "");
		if (sql.endsWith("where")) {
			filtro.setSql(filtro.getSql() + " 1=1 ");
		} else {
			if (!sql.contains("where")) {

				filtro.setSql(filtro.getSql() + " where 1=1 ");
			}
		}
		StringBuilder sb = new StringBuilder(filtro.getSql());
		if (filtro.getPesquisas() != null) {
			filtro.getPesquisas().forEach(p -> {
				String texto = "";
				
				if (p.getComparacao().equals("contem")) {
					texto = p.getNome() + " like ( '%' || " + p.getValor() + " || '%' ) ";
				} else {
					texto = p.getNome() + " " + p.getComparacao() + " " + p.getValor();
				}
				 
				
				
				if (!texto.trim().startsWith("and ")) {
					texto = " and " + texto;
				}
				sb.append(texto);
			});
		}
		if (filtro.getGroupBy() != null) {
			sb.append( " " + filtro.getGroupBy());
		}
		if (filtro.getOrderBy() != null) {
			sb.append( " " + filtro.getOrderBy());
		}
		System.out.println(sb.toString());
		return sb.toString();

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
				if (!StringUtils.isEmpty(pesquisa.getNome()) && !StringUtils.isEmpty(pesquisa.getComparacao())) {
					list.add(novoPredicate(pesquisa, builder, root));
				}
			}
		}
		return list.toArray(new Predicate[list.size()]);
	}

	private Predicate novoPredicate(Pesquisa pesquisa, CriteriaBuilder builder, Root<T> root) {
		// TODO Auto-generated method stub
		if (pesquisa.getValor() != null) {
			pesquisa.setValor(pesquisa.getValor().replaceAll("'", ""));
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		formatter = formatter.withLocale(Locale.getDefault());
		boolean isData = isValidaData(pesquisa.getValor());

		String valor = pesquisa.getValor();

		if ((valor != null) && (ValidadorDigitos.eValidoCNPJ(valor) || ValidadorDigitos.eValidoCPF(valor))) {
			valor = formatarDocumento(valor);
		}
		boolean join = false;
		Path<Long> tabelaJoin = null;
		if ((pesquisa.getTabela() != null) && (!pesquisa.getTabela().trim().equals(""))) {
			try {
				Join<Object, Object> categoriasJoin = root.join(pesquisa.getTabela(), pesquisa.getTipe());
				tabelaJoin = categoriasJoin.get(pesquisa.getNome());
				join = true;
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		if (pesquisa.getComparacao().equals("=")) {
			/*
			 * if (isData) { if (pesquisa.getValor().length() > 10) { return
			 * builder.equal(root.get(pesquisa.getNome()), pesquisa.getValor()); } else {
			 * return builder.equal(root.get(pesquisa.getNome()), pesquisa.getValor()); }
			 * 
			 * } else
			 */
			if (join) {
				return builder.equal(tabelaJoin, valor.toLowerCase().equals("true") ? true
						: valor.toLowerCase().equals("false") ? false : valor);
			} else {
				if (isData) {
					if (pesquisa.getValor().length() > 10) {
						return builder.equal(root.get(pesquisa.getNome()), convertDateTime(pesquisa.getValor()));
					} else {
						return builder.equal(root.get(pesquisa.getNome()), convertDate(pesquisa.getValor()));
					}
				} else
					return builder.equal(root.get(pesquisa.getNome()), getValor(valor));
			}
		}

		if (pesquisa.getComparacao().equals("like")) {

			return builder.like(root.get(pesquisa.getNome()), valor);
		}
		if (pesquisa.getComparacao().equals(">")) {
			if (isData) {
				if (pesquisa.getValor().length() > 10) {
					return builder.greaterThan(root.get(pesquisa.getNome()), convertDateTime(pesquisa.getValor()));
				} else {
					return builder.greaterThan(root.get(pesquisa.getNome()), convertDate(pesquisa.getValor()));
				}
			} else
				return builder.gt(root.get(pesquisa.getNome()), Double.parseDouble(pesquisa.getValor()));
		}
		if (pesquisa.getComparacao().equals(">=")) {
			if (isData) {
				if (pesquisa.getValor().length() > 10) {
					return builder.greaterThanOrEqualTo(root.get(pesquisa.getNome()),
							convertDateTime(pesquisa.getValor()));
				} else {
					return builder.greaterThanOrEqualTo(root.get(pesquisa.getNome()), convertDate(pesquisa.getValor()));
				}
			} else
				return builder.ge(root.get(pesquisa.getNome()), Double.parseDouble(pesquisa.getValor()));
		}
		if (pesquisa.getComparacao().equals("<")) {
			if (isData) {
				if (pesquisa.getValor().length() > 10) {
					return builder.lessThan(root.get(pesquisa.getNome()), convertDateTime(pesquisa.getValor()));
				} else {
					return builder.lessThan(root.get(pesquisa.getNome()), convertDate(pesquisa.getValor()));
				}
			} else
				return builder.lt(root.get(pesquisa.getNome()), Double.parseDouble(pesquisa.getValor()));
		}
		if (pesquisa.getComparacao().equals("<=")) {
			if (isData) {
				if (pesquisa.getValor().length() > 10) {
					return builder.lessThanOrEqualTo(root.get(pesquisa.getNome()),
							convertDateTime(pesquisa.getValor()));
				} else {
					return builder.lessThanOrEqualTo(root.get(pesquisa.getNome()), convertDate(pesquisa.getValor()));
				}
			} else
				return builder.le(root.get(pesquisa.getNome()), Double.parseDouble(pesquisa.getValor()));
		}
		if (pesquisa.getComparacao().trim().equals("is null")) {
			return builder.isNull(root.get(pesquisa.getNome()));
		}

		if (pesquisa.getComparacao().equals("is not null")) {
			return builder.isNotNull(root.get(pesquisa.getNome()));
		}

//		if (pesquisa.getComparacao().equals("=")) {
//			return builder.like(builder.lower(root.get(pesquisa.getNome())), "%"+pesquisa.getValor()+"%");
//		}
		return null;

	}

	private Object getValor(String valor) {
		// TODO Auto-generated method stub
		return valor.toLowerCase().equals("true") ? true : valor.toLowerCase().equals("false") ? false : valor;
	}

	private String formatarDocumento(String valor) {
		// TODO Auto-generated method stub
		valor = retirarFormatacao(valor);

		return CPFCNPJUtil.formatCPForCPNJ(valor);
	}

	private String retirarFormatacao(String campoTexto) {
		return campoTexto.replaceAll("[.-]", "");
	}

	public boolean isValidaData(String dataStr) {
		if (dataStr == null) {
			return false;
		}
		String dateFormat = "dd/MM/uuuu";

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
				.withResolverStyle(ResolverStyle.STRICT);
		try {
			LocalDate.parse(dataStr, dateTimeFormatter);
			return true;
		} catch (DateTimeParseException e) {
			try {
				dateFormat = "dd/MM/uuuu HH:mm";

				dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat).withResolverStyle(ResolverStyle.STRICT);
				LocalDateTime.parse(dataStr, dateTimeFormatter);
				return true;
			} catch (DateTimeParseException e2) {
				return false;
			}
		}
	}

	private LocalDateTime convertDateTime(String value) {
		try {
			String dateFormat = "dd/MM/yyyy HH:mm";

			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
					.withResolverStyle(ResolverStyle.STRICT);
			LocalDateTime date = LocalDateTime.parse(value, dateTimeFormatter);
			return date;
		} catch (DateTimeParseException e2) {
			return LocalDateTime.now();
		}
	}

	private LocalDate convertDate(String value) {
		try {
			String dateFormat = "dd/MM/yyyy";

			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
					.withResolverStyle(ResolverStyle.STRICT);
			LocalDate date = LocalDate.parse(value, dateTimeFormatter);
			return date;
		} catch (DateTimeParseException e2) {
			return LocalDate.now();
		}
	}

	/* funções para jdbcTemplete */

	protected void preencherObjeto(T object, ResultSet rs) {
		try {

			for (Field field : object.getClass().getDeclaredFields()) {
				try {
					if (field.getName().equalsIgnoreCase("serialVersionUID")) {
						continue;
					}
					field.setAccessible(true);
					if (field.getType() == String.class) {
						field.set(object, rs.getString(tratarNomeSql(field.getName())));// field.getType().newInstance();
					}
					if (field.getType() == Long.class) {
						field.set(object, Long.valueOf(rs.getLong(tratarNomeSql(field.getName()))));// field.getType().newInstance();
					}
					if (field.getType() == Integer.class) {
						field.set(object, Integer.valueOf(rs.getInt(tratarNomeSql(field.getName()))));// field.getType().newInstance();
					}

					if (field.getType() == Float.class) {
						field.set(object, Float.valueOf(rs.getFloat(tratarNomeSql(field.getName()))));// field.getType().newInstance();
					}
					if (field.getType() == LocalDateTime.class) {
						field.set(object, LocalDateTime.ofInstant(rs.getTimestamp(tratarNomeSql(field.getName())).toInstant(), ZoneId.systemDefault()));// field.getType().newInstance();
					}

					if (field.getType() == LocalDate.class) {
						//field.set(object, LocalDate.ofInstant(rs.getDate(tratarNomeSql(field.getName())).toInstant(), ZoneId.systemDefault()));// field.getType().newInstance();
					}
					if (field.getType() == LocalTime.class) {
					//	field.set(object, LocalTime.ofInstant(rs.getTime(tratarNomeSql(field.getName())).toInstant(), ZoneId.systemDefault()));
					}
					
					if (field.getType() == byte[].class) {
						field.set(object, rs.getBytes(tratarNomeSql(field.getName())));// field.getType().newInstance();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		} finally {
			// TODO: handle finally clause
		}
	}

	public String tratarNomeSql(String value) {
		if (value.equals(value.toUpperCase())) {
			return value.toLowerCase();
		}
		StringBuilder stringBuilder = new StringBuilder();
		boolean primeira = true;
		for (char letra : value.toCharArray()) {
			stringBuilder.append((Character.isUpperCase(letra) && !primeira ? "_" + Character.toLowerCase(letra)
					: Character.toLowerCase(letra)));
			primeira = false;
		}
		System.out.println(stringBuilder.toString());
		return stringBuilder.toString();
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








	@Override
	public List<T> getList(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
