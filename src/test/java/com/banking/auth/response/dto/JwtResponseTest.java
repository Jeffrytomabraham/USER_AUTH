package com.banking.auth.response.dto;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JwtResponseTest {

    private JwtResponse jwtResponseUnderTest;

    @BeforeEach
    public void setUp() {
        jwtResponseUnderTest = new JwtResponse(null, null, null, null);
    }

    @Test
    public void getJWTResponse(){
    	jwtResponseUnderTest.setEmail("abc@abc.com");
    	jwtResponseUnderTest.setId("id");
    	jwtResponseUnderTest.setSessionToken("token");
    	jwtResponseUnderTest.setUsername("user");
    	
    	Assert.assertEquals(jwtResponseUnderTest.getEmail(), "abc@abc.com");
        Assert.assertEquals(jwtResponseUnderTest.getId(), "id");
        Assert.assertEquals(jwtResponseUnderTest.getSessionToken(), "token");
        Assert.assertEquals(jwtResponseUnderTest.getUsername(), "user");
    }
}
