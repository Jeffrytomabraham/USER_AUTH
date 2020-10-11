package com.banking.auth.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.banking.auth.request.dto.LoginRequest;
import com.banking.auth.request.dto.UserDetailsDTO;
import com.banking.auth.response.dto.AccountDetailsDTO;
import com.banking.auth.response.dto.ErrorResponse;
import com.banking.auth.response.dto.JwtResponse;
import com.banking.auth.security.UserDetailsImpl;
import com.banking.auth.security.jwt.JwtUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.BasicAuthDefinition;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value="/signin",method = RequestMethod.POST,
			consumes="application/json",produces="application/json")
	@ApiOperation(value = "Login to bank account")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail()));
	}
	
	@RequestMapping(value= {"/create/account"},method = RequestMethod.POST)
	@ApiOperation(value = "Create customer account")
	public @ResponseBody ResponseEntity<?> createUserAccount(@RequestBody UserDetailsDTO userDetailsDTO){
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<Object>(userDetailsDTO,headers); 
	    try {    
	        Object accountDetailsDTO= restTemplate.exchange("http://localhost:8080/banking/user/create/account", HttpMethod.POST, entity,Object.class).getBody();
	        return new ResponseEntity<>(accountDetailsDTO,HttpStatus.OK);
		} catch (HttpStatusCodeException ex) {
	    	ErrorResponse errorResponse = new ErrorResponse();
	    	errorResponse.setErrorCode(ex.getStatusCode().toString());
        	errorResponse.setMessage(ex.getResponseBodyAsString());
        	return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(ex.getRawStatusCode()));
		}
	}
	
}
