package com.banking.auth.entity;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class AccountsDTOTest {

    private AccountsDTO accountsDTOUnderTest;

    @BeforeEach
    void setUp() {
        accountsDTOUnderTest = new AccountsDTO();
    }

    @Test
    public void setAccountDTO() {
        LocalDateTime date = LocalDateTime.now();
        accountsDTOUnderTest.setAccountNumber("123");
        accountsDTOUnderTest.setAccountType("SAVINGS");
        accountsDTOUnderTest.setBalance(100);
        accountsDTOUnderTest.setCreationDate(date);
        accountsDTOUnderTest.setDueAmount(0);

        Assert.assertEquals(accountsDTOUnderTest.getAccountNumber(), "123");
        Assert.assertEquals(accountsDTOUnderTest.getAccountType(), "SAVINGS");
        Assert.assertTrue(accountsDTOUnderTest.getBalance() == 100);
        Assert.assertEquals(accountsDTOUnderTest.getCreationDate(), date);
        Assert.assertTrue(accountsDTOUnderTest.getDueAmount() == 0);
    }
}
