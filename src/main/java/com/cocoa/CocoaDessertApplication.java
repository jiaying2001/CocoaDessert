package com.cocoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class CocoaDessertApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocoaDessertApplication.class, args);
	}

}
