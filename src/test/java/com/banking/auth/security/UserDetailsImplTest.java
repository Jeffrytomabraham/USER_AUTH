package com.banking.auth.security;

import com.banking.auth.entity.UserDetailsEntityDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsImplTest {

    @Mock
    private Collection<? extends GrantedAuthority> mockAuthorities;

    private UserDetailsImpl userDetailsImplUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        userDetailsImplUnderTest = new UserDetailsImpl("id", "userName", "email", "password", mockAuthorities);
    }

    @Test
    public void testIsAccountNonExpired() {
        // Setup

        // Run the test
        final boolean result = userDetailsImplUnderTest.isAccountNonExpired();

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testIsAccountNonLocked() {
        // Setup

        // Run the test
        final boolean result = userDetailsImplUnderTest.isAccountNonLocked();

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testIsCredentialsNonExpired() {
        // Setup

        // Run the test
        final boolean result = userDetailsImplUnderTest.isCredentialsNonExpired();

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testIsEnabled() {
        // Setup

        // Run the test
        final boolean result = userDetailsImplUnderTest.isEnabled();

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testEquals() {
        // Setup

        // Run the test
        final boolean result = userDetailsImplUnderTest.equals("o");

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testBuild() {
        // Setup
        final UserDetailsEntityDTO user = new UserDetailsEntityDTO();
        user.setId("id");
        user.setUserName("userName");
        user.setEmail("email");
        user.setPassword("password");
        user.setAccounts(Arrays.asList());

        final UserDetailsImpl expectedResult = new UserDetailsImpl("id", "userName", "email", "password", Arrays.asList());

        // Run the test
        final UserDetailsImpl result = UserDetailsImpl.build(user);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
