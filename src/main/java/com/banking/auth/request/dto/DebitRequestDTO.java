package com.banking.auth.request.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DebitRequestDTO {

	private String username;
	private String accountNumber;
	private double debitAmount;
}
