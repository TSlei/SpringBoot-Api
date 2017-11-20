package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.UserModel;
import com.springboot.service.BaseService;
import com.springboot.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("userController相关Api")
public class UserController {
	
	@Autowired
	private BaseService<UserModel, Integer> baseService;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation("获取用户信息")
	@RequestMapping(value = "/queryUser/{id}", method = RequestMethod.GET)
	public String queryById(@PathVariable("id") Integer id) {
		
		UserModel userModel = baseService.get(id);
		
		return userModel.getName();
	}
	
	@ApiOperation("添加用户")
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public Long insertUser(@RequestParam("id") Long id, @RequestBody UserModel user) {
		
		baseService.save(user);
		
		return id;
	}
	
	@ApiOperation("查询用户")
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String queryUser(@RequestParam("id") Long id) {
		
		UserModel userModel = userService.getById(id);
		
		return userModel.getName();
	}

}
