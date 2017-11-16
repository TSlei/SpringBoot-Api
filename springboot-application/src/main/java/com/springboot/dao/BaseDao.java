package com.springboot.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseDao <E, E_PK> {

	protected String sqlMapNamespace;
	
	@Resource
    private SqlSessionTemplate sqlSessionTemplate;
	
	public E_PK save(final E entity) {
		return (E_PK) save("save", entity);
	}
	
	public Object save(final String statement, final Object entity) {
		Object obj = sqlSessionTemplate.insert(getSqlMapNamespace() + "." + statement, entity);
		return obj;
	}
	
	public void insertBatchAndSetId(final String statement, final Collection<E> recordList) {
		sqlSessionTemplate.insert(getSqlMapNamespace() + "." + statement, recordList);
	}
	
	public int update(final E entity) {
		return update("update", entity);
	}
	
	public int updateData(Object entity) {
		return sqlSessionTemplate.update(getSqlMapNamespace() + ".update", entity);
	}
	
	public int update(String statement, Object entity) {
		return sqlSessionTemplate.update(getSqlMapNamespace() + "." + statement, entity);
	}
	
	
	public int delete(final E_PK id) {
		return delete("delete", id);
	}
	
	public int delete(final List<E_PK> ids) {
		return delete("delete", ids);
	}
	
	public E get(E_PK id) {
		return sqlSessionTemplate.selectOne("select", id);
	}

	public List<E> query(String statement) {
		return sqlSessionTemplate.selectList(statement);
	}
	

	public int delete(final String statement, final Object entity) {
		Map<String, Object> params = createDbidMap();
		if ("delete".equals(statement)) {
			params.put("id", entity);
		} else if ("deletes".equals(statement)) {
			params.put("ids", entity);
		}
		return sqlSessionTemplate.delete(getSqlMapNamespace() + "." + statement, params);
	}
	
	protected String getSqlMapNamespace() {
		if (null == sqlMapNamespace) {
			Class<E> clazz = getGenericClass(getClass(), 1);
			if (null == clazz) {
				throw new RuntimeException("concreate class must provide entity type or the type is incorrect!");
			}
			sqlMapNamespace = clazz.getSimpleName();
		}
		return sqlMapNamespace;
	}
	
	@SuppressWarnings("rawtypes")
	private <T> Class<T> getGenericClass(final Class baseClass, final int index) {
		Type genericType = baseClass.getGenericSuperclass();

		if (genericType instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) genericType).getActualTypeArguments();
			if (null != params && params.length >= index) {
				if (params[index - 1] instanceof Class) {
					return (Class<T>) params[index - 1];
				}
			}
		}
		return null;
	}
	
	protected Map<String, Object> createDbidMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		return params;
	}
	
}
