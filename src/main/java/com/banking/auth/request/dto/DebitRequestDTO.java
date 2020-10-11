package com.banking.auth.request.dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DebitRequestDTO {

	private String username;
	private String accountNumber;
	private double debitAmount;
}
