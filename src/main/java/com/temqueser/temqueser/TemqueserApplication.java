package com.temqueser.temqueser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class TemqueserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemqueserApplication.class, args);
		
	}

}
