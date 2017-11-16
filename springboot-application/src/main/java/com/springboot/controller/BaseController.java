package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.UserModel;
import com.springboot.service.BaseService;

@RestController
public class BaseController {
	
	@Autowired
	private BaseService<UserModel, Integer> baseService;
	
	@RequestMapping(value = "/hello/{id}", method = RequestMethod.GET)
	public String queryById(@PathVariable("id") Integer id) {
		
		UserModel advertisementModel = baseService.get(id);
		
		return advertisementModel.toString();
	}

}
