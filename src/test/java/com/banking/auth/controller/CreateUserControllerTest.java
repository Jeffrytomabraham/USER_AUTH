package com.banking.auth.controller;

import com.banking.auth.config.BankingConfiguration;
import com.banking.auth.request.dto.AddAccountDTO;
import com.banking.auth.request.dto.UserDetailsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserControllerTest {

    private CreateUserController createUserControllerUnderTest;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        createUserControllerUnderTest = new CreateUserController();
        createUserControllerUnderTest.restTemplate = mock(RestTemplate.class);
        createUserControllerUnderTest.bankingConfiguration = mock(BankingConfiguration.class);
        mockMvc= MockMvcBuilders.standaloneSetup(createUserControllerUnderTest).build();
    }

    @Test
    public void testCreateUserAccount() throws Exception{
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();

        when(createUserControllerUnderTest.restTemplate.exchange(eq("nullnull/create/account"),eq(HttpMethod.POST), any(), eq(Object.class))).thenReturn(new ResponseEntity<>("body", HttpStatus.OK));
        MvcResult result1 = mockMvc.perform( MockMvcRequestBuilders
                .post("/banking/user/create/account")
                .header("Authorization","Bearer okdskdskdo")
                .content(asJsonString(userDetailsDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result1.getResponse().getContentAsString();
        Assert.assertEquals(content,"body");
    }

    @Test
    public void testCreateUserAccount_RestTemplateThrowsRestClientException() throws Exception{

        final UserDetailsDTO userDetailsDTO = new UserDetailsDTO();

        HttpHeaders headers = new HttpHeaders();
        String errorMessage = "{\"firstName\":null,\"lastName\":null,\"userName\":null,\"dob\":null,\"age\":0,\"email\":null,\"address1\":null,\"address2\":null,\"postalCode\":null,\"country\":null,\"city\":null,\"phone\":null,\"accounts\":null,\"errorResponse\":{\"errorCode\":\"INVALID_USER\",\"message\":\"User not found\"}}";
        HttpStatusCodeException httpst = new HttpStatusCodeException(HttpStatus.BAD_REQUEST,"",
                null,null,null) {
            @Override
            public HttpStatus getStatusCode() {
                return HttpStatus.BAD_REQUEST;
            }

            @Override
            public int getRawStatusCode() {
                return 400;
            }

            @Override
            public String getStatusText() {
                return "400 BAD_REQUEST";
            }
            @Override
            public String getResponseBodyAsString() {
                return errorMessage;
            }

            @Override
            public String getMessage() {
                return errorMessage;
            }
        };
        when(createUserControllerUnderTest.restTemplate.exchange(eq("nullnull/create/account"),eq(HttpMethod.POST), any(), eq(Object.class))).thenThrow(httpst);
         MvcResult result1 = mockMvc.perform( MockMvcRequestBuilders
                    .post("/banking/user/create/account")
                    .header("Authorization","Bearer okdskdskdo")
                    .content(asJsonString(userDetailsDTO))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertNotNull(result1);
    }

    @Test
    public void testAddUserAccount() throws Exception{
        AddAccountDTO addAccountDTO = new AddAccountDTO();

        when(createUserControllerUnderTest.restTemplate.exchange(eq("nullnull/add/account"),eq(HttpMethod.POST), any(), eq(Object.class))).thenReturn(new ResponseEntity<>("body", HttpStatus.OK));
        MvcResult result1 = mockMvc.perform( MockMvcRequestBuilders
                .post("/banking/user/add/account")
                .header("Authorization","Bearer okdskdskdo")
                .content(asJsonString(addAccountDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result1.getResponse().getContentAsString();
        Assert.assertEquals(content,"body");
    }

    @Test
    public void testAddUserAccount_RestTemplateThrowsRestClientException() throws Exception {

        final AddAccountDTO addAccountDTO = new AddAccountDTO();
        HttpHeaders headers = new HttpHeaders();
        String errorMessage = "{\"firstName\":null,\"lastName\":null,\"userName\":null,\"dob\":null,\"age\":0,\"email\":null,\"address1\":null,\"address2\":null,\"postalCode\":null,\"country\":null,\"city\":null,\"phone\":null,\"accounts\":null,\"errorResponse\":{\"errorCode\":\"INVALID_USER\",\"message\":\"User not found\"}}";
        HttpStatusCodeException httpst = new HttpStatusCodeException(HttpStatus.BAD_REQUEST,"",
                null,null,null) {
            @Override
            public HttpStatus getStatusCode() {
                return HttpStatus.BAD_REQUEST;
            }

            @Override
            public int getRawStatusCode() {
                return 400;
            }

            @Override
            public String getStatusText() {
                return "400 BAD_REQUEST";
            }
            @Override
            public String getResponseBodyAsString() {
                return errorMessage;
            }

            @Override
            public String getMessage() {
                return errorMessage;
            }
        };
        when(createUserControllerUnderTest.restTemplate.exchange(eq("nullnull/add/account"),eq(HttpMethod.POST), any(), eq(Object.class))).thenThrow(httpst);
        MvcResult result1 = mockMvc.perform( MockMvcRequestBuilders
                    .post("/banking/user/add/account")
                    .header("Authorization","Bearer okdskdskdo")
                    .content(asJsonString(addAccountDTO))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertNotNull(result1);
    }

    private  String asJsonString( Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
