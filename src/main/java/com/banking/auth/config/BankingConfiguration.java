package com.banking.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;

@Configuration
@Getter
@PropertySource("classpath:application.properties")
public class BankingConfiguration {

	@Value("${banking.operation.uri}")
	private String bankingOperationURI;
	
	@Value("${banking.operation.parent.path}")
	private String bankingOperationPath;
	
	@Value("${create.user.uri}")
	private String createUserURI;
	
	@Value("${create.user.parent.path}")
	private String createUserPath;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
