package com.banking.auth.security.jwt;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AuthEntryPointJwtTest {

    private AuthEntryPointJwt authEntryPointJwtUnderTest;

    @BeforeEach
    public void setUp() {
        authEntryPointJwtUnderTest = new AuthEntryPointJwt();
    }

    @Test
    public void testCommence() throws Exception {
        final HttpServletRequest request = new MockHttpServletRequest();
        final HttpServletResponse response = new MockHttpServletResponse();
        final AuthenticationException authException = mock(AuthenticationException.class);

        authEntryPointJwtUnderTest.commence(request, response, authException);
        Assert.assertNotNull(authEntryPointJwtUnderTest);
    }

}
