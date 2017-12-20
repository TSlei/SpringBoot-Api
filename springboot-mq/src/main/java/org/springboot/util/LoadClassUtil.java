package org.springboot.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadClassUtil {
	
	public static Properties properties;
	
	public static void loadProperties(){
		InputStream inputStream = LoadClassUtil.class.getClassLoader().getResourceAsStream("azkaban-project.properties");
	    Properties p = new Properties();
	    try{
	        p.load(inputStream);
	    } catch (IOException e1){
	     e1.printStackTrace();
	    }
	    properties = p;
	}
	
	public static void main(String[] args) {
		loadProperties();
	}
	
}
