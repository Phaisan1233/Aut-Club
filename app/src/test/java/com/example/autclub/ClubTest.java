package com.example.autclub;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClubTest {

    private Club club;
    public ClubTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Test : Club class");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        club = new Club();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAndSetUserNameTest() {
        System.out.println("find : testing get and set club ID method");
        int expect = 4;
        club.setClubID(expect);
        int result = club.getClubID();
        assertEquals(expect, result);
    }

    @Test
    public void getAndSetFirstNameTest() {
        System.out.println("find : testing get and set club name method");
        String expect = "AUTMSA";
        club.setName(expect);
        String result = club.getName();
        assertEquals(expect, result);
    }

    @Test
    public void getAndSetEmailTest() {
        System.out.println("find : testing get and set club fb tokens method");
        String expect = "iqnboneqbmoqnmpqnmpmnq";
        club.setTokens(expect);
        String result = club.getTokens();
        assertEquals(expect, result);
    }
}