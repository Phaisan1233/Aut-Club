package com.example.autclub;

import com.example.autclub.AppModel.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void getAndSetUserNameTest() {
        System.out.println("find : testing get and set username method");
        String expect = "admin";
        user.setUserName(expect);
        String result = user.getUserName();
        assertEquals(expect, result);
    }

    @Test
    public void getAndSetFirstNameTest() {
        System.out.println("find : testing get and set first name method");
        String expect = "Bob";
        user.setFirstName(expect);
        String result = user.getFirstName();
        assertEquals(expect, result);
    }

    @Test
    public void getAndSetLastNameTest() {
        System.out.println("find : testing get and set last name method");
        String expect = "jame";
        user.setLastName(expect);
        String result = user.getLastName();
        assertEquals(expect, result);
    }

    @Test
    public void getAndSetEmailTest() {
        System.out.println("find : testing get and set email method");
        String expect = "admin@gmail.com";
        user.setEmail(expect);
        String result = user.getEmail();
        assertEquals(expect, result);
    }

    @Test
    public void getAndSetTimeTest() {
        System.out.println("find : testing get and set time method");
        String expect = "20190506131549.466";
        user.setTimeStamp(expect);
        double result = user.getTimeStamp();
        assertEquals(expect, result, 0);
    }

}