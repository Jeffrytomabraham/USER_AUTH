package com.banking.auth.response.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountsDTO {

	private String accountType;
	private String accountNumber;
	private double balance;
	private LocalDateTime creationDate;
	private double dueAmount;
}
