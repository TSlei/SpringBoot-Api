package com.springboot.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.model.UserModel;

@Mapper
public interface  UserDao {

	public int save(@Param("user") UserModel user);
	
	public void saveList(@Param("users") Collection<UserModel> recordList);
	
	public int update(@Param("user") UserModel user);
	
	public int delete(@Param("id") Integer id);
	
	public int deleteByIds(@Param("ids") List<Integer> ids);
	
	public UserModel getById(@Param("id") Long id);

	public List<UserModel> query();
	
}
