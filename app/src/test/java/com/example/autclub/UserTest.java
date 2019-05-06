package com.example.autclub;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private User user;


    public UserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Test : User class");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        user = new User();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getSetUserNameTest() {
        System.out.println("find : testing get and set username method");
        String expect = "admin";
        user.setUserName(expect);
        String result = user.getUserName();
        assertEquals(expect,result);
    }

    @Test
    public void setUserName() {
    }

    @Test
    public void getFirstName() {
    }

    @Test
    public void setFirstName() {
    }

    @Test
    public void getLastName() {
    }

    @Test
    public void setLastName() {
    }

    @Test
    public void getEmail() {
    }

    @Test
    public void setEmail() {
    }
}