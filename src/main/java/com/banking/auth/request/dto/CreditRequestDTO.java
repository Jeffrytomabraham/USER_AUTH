package com.banking.auth.request.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditRequestDTO {

	private String username;
	private String accountNumber;
	private double creditAmount;
}
