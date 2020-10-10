package com.banking.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class AccountAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountAuthenticationApplication.class, args);
	}

}
