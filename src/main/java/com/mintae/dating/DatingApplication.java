package com.mintae.dating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class DatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatingApplication.class, args);
	}

}
