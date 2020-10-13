package com.banking.auth.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {

	private String SessionToken;
	private String id;
	private String username;
	private String email;
}
