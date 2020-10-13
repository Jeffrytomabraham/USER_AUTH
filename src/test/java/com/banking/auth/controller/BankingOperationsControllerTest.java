package com.banking.auth.controller;

import com.banking.auth.config.BankingConfiguration;
import com.banking.auth.request.dto.CreditRequestDTO;
import com.banking.auth.request.dto.DebitRequestDTO;
import com.banking.auth.request.dto.ViewAccountDTO;
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

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankingOperationsControllerTest {

    private BankingOperationsController bankingOperationsControllerUnderTest;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        bankingOperationsControllerUnderTest = new BankingOperationsController();
        bankingOperationsControllerUnderTest.restTemplate = mock(RestTemplate.class);
        bankingOperationsControllerUnderTest.bankingConfiguration = mock(BankingConfiguration.class);
        mockMvc= MockMvcBuilders.standaloneSetup(bankingOperationsControllerUnderTest).build();
    }

    @Test
    public void testCreditOperation() throws Exception{

        CreditRequestDTO creditRequestDTO = new CreditRequestDTO();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer okdskdskdo");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<Object>(creditRequestDTO,headers);
        when(bankingOperationsControllerUnderTest.restTemplate.exchange(eq("nullnull/credit"),eq(HttpMethod.POST), any(), eq(Object.class))).thenReturn(new ResponseEntity<>("body", HttpStatus.OK));

        MvcResult result1 = mockMvc.perform( MockMvcRequestBuilders
                .post("/banking/operations/credit")
                .header("Authorization","Bearer okdskdskdo")
                .content(asJsonString(creditRequestDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result1.getResponse().getContentAsString();
        Assert.assertEquals(content,"body");
    }

    @Test
    public void testCreditOperation_RestTemplateThrowsRestClientException() throws Exception {
        final CreditRequestDTO creditRequestDTO = new CreditRequestDTO();

        when(bankingOperationsControllerUnderTest.restTemplate.exchange(eq("nullnull/credit"),eq(HttpMethod.POST), any(), eq(Object.class))).thenThrow(RestClientResponseException.class);

        Exception exception = assertThrows(NestedServletException.class, () -> {
        	MvcResult result1 = mockMvc.perform( MockMvcRequestBuilders
                    .post("/banking/operations/credit")
                    .header("Authorization","Bearer okdskdskdo")
                    .content(asJsonString(creditRequestDTO))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)).andReturn();
        });
        
   }

    @Test
    public void testDebitOperation() throws Exception{

        final DebitRequestDTO debitRequestDTO =  new DebitRequestDTO();

        when(bankingOperationsControllerUnderTest.restTemplate.exchange(eq("nullnull/debit"),eq(HttpMethod.POST), any(), eq(Object.class))).thenReturn(new ResponseEntity<>("body", HttpStatus.OK));

        MvcResult result1 = mockMvc.perform( MockMvcRequestBuilders
                .post("/banking/operations/debit")
                .header("Authorization","Bearer okdskdskdo")
                .content(asJsonString(debitRequestDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result1.getResponse().getContentAsString();
        Assert.assertEquals(content,"body");
    }

    @Test
    public void testDebitOperation_RestTemplateThrowsRestClientException() {

        DebitRequestDTO debitRequestDTO = new DebitRequestDTO();

        when(bankingOperationsControllerUnderTest.restTemplate.exchange(eq("nullnull/credit"),eq(HttpMethod.POST), any(), eq(Object.class))).thenThrow(RestClientResponseException.class);

        Exception exception = assertThrows(NestedServletException.class, () -> {
            MvcResult result1 = mockMvc.perform( MockMvcRequestBuilders
                    .post("/banking/operations/debit")
                    .header("Authorization","Bearer okdskdskdo")
                    .content(asJsonString(debitRequestDTO))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)).andReturn();
        });
    }

    @Test
    public void testAccountDetails() throws Exception{
        // Setup
        final ViewAccountDTO viewAccountDTO = new ViewAccountDTO();

        when(bankingOperationsControllerUnderTest.restTemplate.exchange(eq("nullnull/account/details"),eq(HttpMethod.POST), any(), eq(Object.class))).thenReturn(new ResponseEntity<>("body", HttpStatus.OK));

        MvcResult result1 = mockMvc.perform( MockMvcRequestBuilders
                .post("/banking/operations/account/details")
                .header("Authorization","Bearer okdskdskdo")
                .content(asJsonString(viewAccountDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result1.getResponse().getContentAsString();
        Assert.assertEquals(content,"body");
    }

    @Test
    public void testAccountDetails_RestTemplateThrowsRestClientException() {

        final ViewAccountDTO viewAccountDTO = new ViewAccountDTO();

        when(bankingOperationsControllerUnderTest.restTemplate.exchange(eq("url"), eq(HttpMethod.GET), eq(new HttpEntity<>(null)), eq(Object.class), any(Object.class))).thenThrow(RestClientResponseException.class);
        when(bankingOperationsControllerUnderTest.restTemplate.exchange(eq("nullnull/credit"),eq(HttpMethod.POST), any(), eq(Object.class))).thenThrow(RestClientException.class);

        Exception exception = assertThrows(NestedServletException.class, () -> {
            MvcResult result1 = mockMvc.perform( MockMvcRequestBuilders
                    .post("/banking/operations/account/details")
                    .header("Authorization","Bearer okdskdskdo")
                    .content(asJsonString(viewAccountDTO))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)).andReturn();
        });
    }
    private  String asJsonString( Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
