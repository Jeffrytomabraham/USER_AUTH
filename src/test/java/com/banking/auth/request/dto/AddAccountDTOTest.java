package com.banking.auth.request.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AddAccountDTOTest {

    private AddAccountDTO addAccountDTOUnderTest;

    @BeforeEach
    public void setUp() {
        addAccountDTOUnderTest = new AddAccountDTO();
    }
    @Test
    public void setAddAccount(){
        addAccountDTOUnderTest.setAccountType("Savings");
        addAccountDTOUnderTest.setCreditAmount(100);
        addAccountDTOUnderTest.setUserName("username");

        assertEquals(addAccountDTOUnderTest.getAccountType(), "Savings");
        assertTrue(addAccountDTOUnderTest.getCreditAmount()==100);
        assertEquals(addAccountDTOUnderTest.getUserName(), "username");
    }
}
