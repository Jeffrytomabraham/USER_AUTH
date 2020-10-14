package com.banking.auth.controller;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.banking.auth.config.BankingConfiguration;
import com.banking.auth.request.dto.AddAccountDTO;
import com.banking.auth.request.dto.UserDetailsDTO;
import com.banking.auth.response.dto.ErrorResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/banking/user")
public class CreateUserController {
	
	private static final String AUTHORIZATION = "Authorization";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	BankingConfiguration bankingConfiguration;

	@PostMapping(value= {"/create/account"},consumes="application/json",produces="application/json")
	@ApiOperation(value = "Create customer account")
	public @ResponseBody ResponseEntity<?> createUserAccount(@RequestHeader (value="Authorization") String authorization,@RequestBody UserDetailsDTO userDetailsDTO){
		HttpHeaders headers = new HttpHeaders();
		headers.add(AUTHORIZATION, authorization);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<Object>(userDetailsDTO,headers); 
	    try { 
	    	String generateURI =  bankingConfiguration.getCreateUserURI()+bankingConfiguration.getCreateUserPath();
	        Object accountDetailsDTO= restTemplate.exchange(generateURI+"/create/account", HttpMethod.POST, entity,Object.class).getBody();
	        return new ResponseEntity<>(accountDetailsDTO,HttpStatus.OK);
		} catch (HttpStatusCodeException ex) {
			ErrorResponse errorResponse = getErrorMessage(ex.getResponseBodyAsString());
			return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(ex.getRawStatusCode()));
		}
	}
	
	@PostMapping(value= {"/add/account"},consumes="application/json",produces="application/json")
	@ApiOperation(value = "Create another customer account")
	public @ResponseBody ResponseEntity<?> addUserAccount(@RequestHeader (value="Authorization") String authorization,@RequestBody AddAccountDTO addAccountDTO){
		HttpHeaders headers = new HttpHeaders();
		headers.add(AUTHORIZATION, authorization);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<Object>(addAccountDTO,headers); 
	    try {    
	    	String generateURI =  bankingConfiguration.getCreateUserURI()+bankingConfiguration.getCreateUserPath();
	        Object accountDetailsDTO= restTemplate.exchange(generateURI+"/add/account", HttpMethod.POST, entity,Object.class).getBody();
	        return new ResponseEntity<>(accountDetailsDTO,HttpStatus.OK);
		} catch (HttpStatusCodeException ex) {
	    	ErrorResponse errorResponse = getErrorMessage(ex.getResponseBodyAsString());
        	return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(ex.getRawStatusCode()));
		}
	}

	private ErrorResponse getErrorMessage(String message){
		ErrorResponse errorResponse = new ErrorResponse();
		JSONObject jsonObject = new JSONObject(message);
		JSONObject errorFromApi = jsonObject.getJSONObject("errorResponse");
		errorResponse.setErrorCode(errorFromApi.getString("errorCode"));
		errorResponse.setMessage(errorFromApi.getString("message"));
		return errorResponse;
	}
}
