package com.banking.auth.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.banking.auth.request.dto.CreditRequestDTO;
import com.banking.auth.request.dto.DebitRequestDTO;
import com.banking.auth.response.dto.ErrorResponse;
import com.banking.auth.response.dto.UpdatedAccountDetails;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/banking/operations")
public class BankingOperationsController {
	
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value= {"/credit"},method = RequestMethod.POST,
			consumes="application/json",produces="application/json")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@ApiOperation(value = "Add credit to account")
	public @ResponseBody ResponseEntity<?> creditOperation(@RequestHeader (value="Authorization") String authorization, @RequestBody CreditRequestDTO creditRequestDTO){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<Object>(creditRequestDTO,headers); 
        try {
        	Object updatedAccountDetails= restTemplate.exchange("http://localhost:9090/banking/operations/credit", HttpMethod.POST, entity,Object.class).getBody();
        	return new ResponseEntity<>(updatedAccountDetails,HttpStatus.OK);
        } catch (HttpStatusCodeException ex) {
        	ErrorResponse errorResponse = new ErrorResponse();
        	errorResponse.setErrorCode(ex.getStatusCode().toString());
        	errorResponse.setMessage(ex.getResponseBodyAsString());
        	return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(ex.getRawStatusCode()));
        }
	}
	
	@RequestMapping(value= {"/debit"},method = RequestMethod.POST,
			consumes="application/json",produces= "application/json")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<?> debitOperation(@RequestHeader (value="Authorization") String authorization,@RequestBody DebitRequestDTO debitRequestDTO){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<Object>(debitRequestDTO,headers); 
        try {
        	Object updatedAccountDetails= restTemplate.exchange("http://localhost:9090/banking/operations/debit", HttpMethod.POST, entity,Object.class).getBody();
        	return new ResponseEntity<>(updatedAccountDetails,HttpStatus.OK);
        } catch (HttpStatusCodeException ex) {
        	ErrorResponse errorResponse = new ErrorResponse();
        	errorResponse.setErrorCode(ex.getStatusCode().toString());
        	errorResponse.setMessage(ex.getResponseBodyAsString());
        	return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(ex.getRawStatusCode()));
        }
	}	
	
}
