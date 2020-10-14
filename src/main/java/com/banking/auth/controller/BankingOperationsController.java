package com.banking.auth.controller;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.banking.auth.config.BankingConfiguration;
import com.banking.auth.request.dto.CreditRequestDTO;
import com.banking.auth.request.dto.DebitRequestDTO;
import com.banking.auth.request.dto.ViewAccountDTO;
import com.banking.auth.response.dto.ErrorResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/banking/operations")
public class BankingOperationsController {
	
	private Log log = LogFactory.getLog(BankingOperationsController.class);
	
	private static final String AUTHORIZATION = "Authorization";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	BankingConfiguration bankingConfiguration;

	@PostMapping(value= {"/credit"},
			consumes="application/json",produces="application/json")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@ApiOperation(value = "Add credit to account")
	public @ResponseBody ResponseEntity<?> creditOperation(@RequestHeader (value="Authorization") String authorization, @RequestBody CreditRequestDTO creditRequestDTO){
		log.info("Authenticating user to credit account balance");
		HttpHeaders headers = new HttpHeaders();
		headers.add(AUTHORIZATION, authorization);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<Object>(creditRequestDTO,headers); 
        try {
        	String generateURI =  bankingConfiguration.getBankingOperationURI()+bankingConfiguration.getBankingOperationPath();
        	Object updatedAccountDetails= restTemplate.exchange(generateURI+"/credit", HttpMethod.POST, entity,Object.class).getBody();
        	return new ResponseEntity<>(updatedAccountDetails,HttpStatus.OK);
        } catch (HttpStatusCodeException ex) {
        	log.info("Validation failed");
			ErrorResponse errorResponse = getErrorMessage(ex.getResponseBodyAsString());
			return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(ex.getRawStatusCode()));
        }
	}
	
	@PostMapping(value= {"/debit"},
			consumes="application/json",produces= "application/json")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@ApiOperation(value = "Debit cash from account")
	public @ResponseBody ResponseEntity<?> debitOperation(@RequestHeader (value="Authorization") String authorization,@RequestBody DebitRequestDTO debitRequestDTO){
		log.info("Authenticating user to debit account balance");
		HttpHeaders headers = new HttpHeaders();
		headers.add(AUTHORIZATION, authorization);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<Object>(debitRequestDTO,headers); 
        try {
        	String generateURI =  bankingConfiguration.getBankingOperationURI()+bankingConfiguration.getBankingOperationPath();
        	Object updatedAccountDetails= restTemplate.exchange(generateURI+"/debit", HttpMethod.POST, entity,Object.class).getBody();
        	return new ResponseEntity<>(updatedAccountDetails,HttpStatus.OK);
        } catch (HttpStatusCodeException ex) {
        	log.info("Validation failed");
			ErrorResponse errorResponse = getErrorMessage(ex.getResponseBodyAsString());
			return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(ex.getRawStatusCode()));
        }
	}
	
	@PostMapping(value= {"/account/details"},
			consumes="application/json",produces= "application/json")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@ApiOperation(value = "Get account details")
	public @ResponseBody ResponseEntity<?> accountDetails(@RequestHeader (value="Authorization") String authorization,@RequestBody ViewAccountDTO viewAccountDTO){
		log.info("Authenticating user to view details");
		HttpHeaders headers = new HttpHeaders();
		headers.add(AUTHORIZATION, authorization);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<Object>(viewAccountDTO,headers); 
        try {
        	String generateURI =  bankingConfiguration.getBankingOperationURI()+bankingConfiguration.getBankingOperationPath();
        	Object updatedAccountDetails= restTemplate.exchange(generateURI+"/account/details", HttpMethod.POST, entity,Object.class).getBody();
        	return new ResponseEntity<>(updatedAccountDetails,HttpStatus.OK);
        } catch (HttpStatusCodeException ex) {
        	log.info("Validation failed");
			ErrorResponse errorResponse = getErrorMessage(ex.getResponseBodyAsString());
			return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(ex.getRawStatusCode()));
        }
	}

	private ErrorResponse getErrorMessage(String message){
		ErrorResponse errorResponse = new ErrorResponse();
		JSONObject jsonObject = new JSONObject(message);
		JSONObject errorFromApi = jsonObject.getJSONObject("error");
		errorResponse.setErrorCode(errorFromApi.getString("errorCode"));
		errorResponse.setMessage(errorFromApi.getString("message"));
		return errorResponse;
	}
	
}
