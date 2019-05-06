package com.example.autclub;

import com.example.autclub.AppModel.Event;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class EventTest {
    private Event event;
    public EventTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Test : Event class");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        event = new Event();
    }

    @After
    public void tearDown() {
    }


    @Test
    public void dateFormatter() {
        String input = "2019-05-09T19:00:00+1200";
        String expect = "09-05-2019 19:00:00";
        String result = "";
        try {
            result = event.DateFormatter(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(expect,result);

    }
}