package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.dao.sessionFactory.BaseDao;

@Component
public class BaseService<E, E_PK> {

	@Autowired
	protected BaseDao<E, E_PK> dao;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public E_PK save(final E entity) throws DataAccessException {
		return dao.save(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveList(final List<E> entity) throws DataAccessException {
		List<E> insertList = new ArrayList<E>();
		for (E e : entity) {
			if (null != e) {
				insertList.add(e);
			}
		}
		if (!insertList.isEmpty()) {
			dao.saveList(insertList);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int update(final E entity) throws DataAccessException {
		return dao.update(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int update(final List<E> entity) throws DataAccessException {
		int rowsEffected = 0;
		for (E e : entity)
			rowsEffected += null == e ? 0 : update(e);
		return rowsEffected;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int delete(final E_PK id) throws DataAccessException {
		return dao.delete(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int delete(final List<E_PK> ids) throws DataAccessException {
		int rowsEffected = 0;
		for (E_PK e : ids)
			rowsEffected += null == e ? 0 : delete(e);
		return rowsEffected;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int deleteByIds(final List<E_PK> ids) throws DataAccessException {
		return dao.deleteByIds(ids);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public E get(final E_PK id) throws DataAccessException {
		return dao.get(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<E> query() throws DataAccessException {
		return dao.query("query");
	}

}
