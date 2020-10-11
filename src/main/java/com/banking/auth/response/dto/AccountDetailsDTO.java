package com.banking.auth.response.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDetailsDTO {

	private String firstName;
	private String lastName;
	private String userName;
	private String dob;
	private int age;
	private String email;
	private String address1;
	private String address2;
	private String postalCode;
	private String country;
	private String city;
	private String phone;
	private List<AccountsDTO> accounts;
	private ErrorResponse errorResponse;
	private HttpStatus httpStatus;
}
