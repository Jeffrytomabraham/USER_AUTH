package com.banking.auth.response.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private String errorCode;
	private String message;
	
}
