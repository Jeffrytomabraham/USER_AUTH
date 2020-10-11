package com.banking.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
public class AccountAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountAuthenticationApplication.class, args);
	}

}
