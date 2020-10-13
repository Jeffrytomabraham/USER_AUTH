package com.banking.auth.controller;

import com.banking.auth.config.BankingConfiguration;
import com.banking.auth.request.dto.LoginRequest;
import com.banking.auth.response.dto.JwtResponse;
import com.banking.auth.security.UserDetailsImpl;
import com.banking.auth.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {

    private AuthenticationController authenticationControllerUnderTest;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        authenticationControllerUnderTest = new AuthenticationController();
        authenticationControllerUnderTest.authenticationManager = mock(AuthenticationManager.class);
        authenticationControllerUnderTest.encoder = mock(PasswordEncoder.class);
        authenticationControllerUnderTest.jwtUtils = mock(JwtUtils.class);
        authenticationControllerUnderTest.restTemplate = mock(RestTemplate.class);
        authenticationControllerUnderTest.bankingConfiguration = mock(BankingConfiguration.class);
        mockMvc= MockMvcBuilders.standaloneSetup(authenticationControllerUnderTest).build();
    }

    @Test
    public void testAuthenticateUser() throws Exception{
         LoginRequest loginRequest = new LoginRequest();
         loginRequest.setPassword("pass");
         loginRequest.setUsername("user");
         Authentication result2 = mock(Authentication.class); 
        when(authenticationControllerUnderTest.authenticationManager.authenticate(any())).thenReturn(result2);
        when(authenticationControllerUnderTest.jwtUtils.generateJwtToken(any())).thenReturn("result");
        UserDetailsImpl userImpl = new UserDetailsImpl("string", "string", "string", "string", null);
        		
        when(result2.getPrincipal()).thenReturn(userImpl);
        JwtResponse jwt = new JwtResponse("result", "string", "string", "string");
       
        MvcResult result1 = mockMvc.perform( MockMvcRequestBuilders
                .post("/api/auth/signin")
                .content(asJsonString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result1.getResponse().getContentAsString();
        Assert.assertEquals(content,asJsonString(jwt));
    }



    private  String asJsonString( Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
