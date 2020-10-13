package com.banking.auth.response.dto;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ErrorResponseTest {

    private ErrorResponse errorResponseUnderTest;

    @BeforeEach
    public void setUp() {
        errorResponseUnderTest = new ErrorResponse();
    }

    @Test
    public void setErrorResponse(){
        errorResponseUnderTest.setErrorCode("DUPLICATE_USER");
        errorResponseUnderTest.setMessage("Duplicate user");

        Assert.assertEquals(errorResponseUnderTest.getErrorCode(), "DUPLICATE_USER");
        Assert.assertEquals(errorResponseUnderTest.getMessage(), "Duplicate user");
    }
}
