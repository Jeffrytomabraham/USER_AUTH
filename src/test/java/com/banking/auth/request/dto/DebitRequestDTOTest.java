package com.banking.auth.request.dto;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DebitRequestDTOTest {

    private DebitRequestDTO debitRequestDTOUnderTest;

    @BeforeEach
    public void setUp() {
        debitRequestDTOUnderTest = new DebitRequestDTO();
    }

    @Test
    public void setDebitRequest(){

        debitRequestDTOUnderTest.setAccountNumber("123");
        debitRequestDTOUnderTest.setDebitAmount(100);
        debitRequestDTOUnderTest.setUsername("user");


        Assert.assertEquals(debitRequestDTOUnderTest.getAccountNumber(), "123");
        Assert.assertEquals(debitRequestDTOUnderTest.getUsername(), "user");
        Assert.assertTrue(debitRequestDTOUnderTest.getDebitAmount() == 100);
    }
}
