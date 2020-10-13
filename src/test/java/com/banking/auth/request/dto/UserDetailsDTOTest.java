package com.banking.auth.request.dto;

import com.banking.auth.entity.AccountsDTO;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsDTOTest {

    private UserDetailsDTO userDetailsEntityDTOUnderTest;

    @BeforeEach
    public void setUp() {
        userDetailsEntityDTOUnderTest = new UserDetailsDTO();
    }

    @Test
    public void setUserDetails(){
        userDetailsEntityDTOUnderTest.setAddress1("temp1");
        userDetailsEntityDTOUnderTest.setAddress2("temp1");
        userDetailsEntityDTOUnderTest.setAge(10);
        userDetailsEntityDTOUnderTest.setCountry("India");
        userDetailsEntityDTOUnderTest.setCity("tvm");
        userDetailsEntityDTOUnderTest.setDob("02-02-2020");
        userDetailsEntityDTOUnderTest.setEmail("abc@abc.com");
        userDetailsEntityDTOUnderTest.setUserName("username");
        userDetailsEntityDTOUnderTest.setPostalCode("698754");
        userDetailsEntityDTOUnderTest.setFirstName("firstname");
        userDetailsEntityDTOUnderTest.setLastName("lastname");
        userDetailsEntityDTOUnderTest.setPhone("123654789");
        userDetailsEntityDTOUnderTest.setPassword("123654789");
        userDetailsEntityDTOUnderTest.setAccountType("savings");
        userDetailsEntityDTOUnderTest.setCreditAmount(100);


        Assert.assertEquals(userDetailsEntityDTOUnderTest.getUserName(), "username");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getFirstName(), "firstname");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getLastName(), "lastname");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getPhone(), "123654789");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getPostalCode(), "698754");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getAddress1(), "temp1");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getAddress2(), "temp1");
        Assert.assertTrue(userDetailsEntityDTOUnderTest.getAge()==10);
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getCity(), "tvm");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getCountry(), "India");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getDob(), "02-02-2020");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getEmail(), "abc@abc.com");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getPassword(), "123654789");
        Assert.assertTrue(userDetailsEntityDTOUnderTest.getCreditAmount()==100);
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getAccountType(), "savings");
    }
}
