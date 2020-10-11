package com.banking.auth.request.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserDetailsDTO {

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
	
	private String password;
	
	private String accountType;
	
	private double creditAmount;
}
