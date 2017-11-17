package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.dao.annotation.UserDao;
import com.springboot.model.UserModel;

@Component
public class UserService {
	
	 @Autowired
	 private UserDao userMapper;

	 public UserModel getById(Long id) {
	     return userMapper.getById(id);
	 }
}
