package com.magna.aeroporto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AeroportoApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(AeroportoApplication.class, args);
	}
}
