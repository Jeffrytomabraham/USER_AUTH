package com.banking.auth.request.dto;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ViewAccountDTOTest {

    private ViewAccountDTO viewAccountDTOUnderTest;

    @BeforeEach
    public void setUp() {
        viewAccountDTOUnderTest = new ViewAccountDTO();
    }

    @Test
    public void setViewAccount(){
    	viewAccountDTOUnderTest.setAccountNumber("123");
    	viewAccountDTOUnderTest.setUsername("username");
    	
    	Assert.assertEquals(viewAccountDTOUnderTest.getUsername(), "username");
        Assert.assertEquals(viewAccountDTOUnderTest.getAccountNumber(), "123");
    }
}
