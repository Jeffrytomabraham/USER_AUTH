package com.banking.auth.request.dto;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginRequestTest {

    private LoginRequest loginRequestUnderTest;

    @BeforeEach
    public void setUp() {
        loginRequestUnderTest = new LoginRequest();
    }

    @Test
    public void setUserDetails(){
    	loginRequestUnderTest.setPassword("pass");
    	loginRequestUnderTest.setUsername("user");
    	
    	Assert.assertEquals(loginRequestUnderTest.getPassword(), "pass");
        Assert.assertEquals(loginRequestUnderTest.getUsername(), "user");
    }
}
