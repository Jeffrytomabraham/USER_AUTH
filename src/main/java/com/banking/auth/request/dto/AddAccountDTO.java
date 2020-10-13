package com.banking.auth.request.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAccountDTO {

	private String userName;
	private String accountType;
	private double creditAmount;
}
