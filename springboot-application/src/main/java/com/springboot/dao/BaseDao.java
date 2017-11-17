package com.springboot.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseDao <E, E_PK> {

	protected String sqlMapNamespace;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public E_PK save(final E entity) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Object obj = sqlSession.insert("save", entity);
		return (E_PK)obj;
	}
	
	public void saveList(final Collection<E> recordList) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("insertList", recordList);
	}
	
	public int update(final E entity) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.update("update", entity);
	}
	
	public int delete(final E_PK id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.delete("delete", id);
	}
	
	public int deleteByIds(final List<E_PK> ids) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.delete("deleteByIds", ids);
	}
	
	public E get(E_PK id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectOne("selectUserById", id);
	}

	public List<E> query(String statement) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList(statement);
	}
	
}
