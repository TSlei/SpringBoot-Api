package com.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@ComponentScan
public class Application implements CommandLineRunner {

	
	@RequestMapping("/") 
	@ResponseBody
	public String index() { 
		return "Spring Boot Application...";
	}
	
    public static void main(String[] args) {
        System.out.println("starting1...");
        SpringApplication.run(Application.class, args);
        System.out.println("starting2...");
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("running...");
    }

}