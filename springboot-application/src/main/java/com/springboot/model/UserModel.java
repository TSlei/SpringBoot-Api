package com.springboot.model;

import java.io.Serializable;

/**
 * 
 * @author leo
 * @version 1.0
 * @created 2017-11-16 下午4:45:28
 * 
 */
public class UserModel implements Serializable{

	private static final long serialVersionUID = -5488763985601355793L;

	private int id;
	
	private String name;

	private int age;

	private int hight;

	private String sex;
	
	private String description;

	private String birthday;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
