package com.banking.auth.response.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatedAccountDetails {

	private String accountNumber;
	private double balance;
	private HttpStatus httpStatus;
	private ErrorResponse error;
}
