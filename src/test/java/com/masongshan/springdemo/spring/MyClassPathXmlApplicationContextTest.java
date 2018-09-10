package com.masongshan.springdemo.spring;

import org.junit.Assert;
import org.junit.Test;

public class MyClassPathXmlApplicationContextTest {
    private MyClassPathXmlApplicationContext context;

    @org.junit.Before
    public void setUp() throws Exception {
        context = new MyClassPathXmlApplicationContext("applicationContext.xml");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetInstance() throws Exception {
        Assert.assertNotNull(context.getInstance("user"));
    }
}