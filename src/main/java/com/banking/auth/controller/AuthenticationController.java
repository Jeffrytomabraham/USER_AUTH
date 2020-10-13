package com.banking.auth.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.banking.auth.config.BankingConfiguration;
import com.banking.auth.request.dto.LoginRequest;
import com.banking.auth.response.dto.JwtResponse;
import com.banking.auth.security.UserDetailsImpl;
import com.banking.auth.security.jwt.JwtUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	private Log log = LogFactory.getLog(AuthenticationController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	BankingConfiguration bankingConfiguration;

	@PostMapping(value="/signin",
			consumes="application/json",produces="application/json")
	@ApiOperation(value = "Login to bank account")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		log.info("Authenticating user login");
		log.debug("Authenticating user login for user -"+loginRequest.getUsername());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		log.info("Exting after user authentication");
		log.debug("Exting after user authentication fot user -"+loginRequest.getUsername());
		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail()));
	}
	
}
