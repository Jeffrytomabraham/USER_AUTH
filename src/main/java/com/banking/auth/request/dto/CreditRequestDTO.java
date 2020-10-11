package com.banking.auth.request.dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CreditRequestDTO {

	private String username;
	private String accountNumber;
	private double creditAmount;
}
