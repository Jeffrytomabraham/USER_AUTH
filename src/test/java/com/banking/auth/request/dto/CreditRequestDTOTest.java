package com.banking.auth.request.dto;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreditRequestDTOTest {

    private CreditRequestDTO creditRequestDTOUnderTest;

    @BeforeEach
    public void setUp() {
        creditRequestDTOUnderTest = new CreditRequestDTO();
    }

    @Test
    public void setCreditRequest(){
        creditRequestDTOUnderTest.setAccountNumber("123");
        creditRequestDTOUnderTest.setCreditAmount(100);
        creditRequestDTOUnderTest.setUsername("user");

        Assert.assertEquals(creditRequestDTOUnderTest.getAccountNumber(), "123");
        Assert.assertEquals(creditRequestDTOUnderTest.getUsername(), "user");
        Assert.assertTrue(creditRequestDTOUnderTest.getCreditAmount() == 100);
    }

}
