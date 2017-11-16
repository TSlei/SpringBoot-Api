package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.dao.BaseDao;

@Component
public abstract class BaseService<E, E_PK> {

	@Autowired
	protected abstract BaseDao<E, E_PK> getDao();

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public E_PK save(final E entity) throws DataAccessException {
		return getDao().save(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(final List<E> entity) throws DataAccessException {
		List<E> insertList = new ArrayList<E>();
		for (E e : entity) {
			if (null != e) {
				insertList.add(e);
			}
		}
		if (!insertList.isEmpty()) {
			getDao().insertBatchAndSetId("saveList",insertList);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int update(final E entity) throws DataAccessException {
		return getDao().update(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateData(final E entity) throws DataAccessException {
		return getDao().updateData(entity);
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
		return getDao().delete(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int delete(final List<E_PK> ids) throws DataAccessException {
		int rowsEffected = 0;
		for (E_PK e : ids)
			rowsEffected += null == e ? 0 : delete(e);
		return rowsEffected;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int deletes(final List<E_PK> ids) throws DataAccessException {
		return getDao().delete(ids);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public E get(final E_PK id) throws DataAccessException {
		return getDao().get(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<E> query() throws DataAccessException {
		return getDao().query("query");
	}

}
