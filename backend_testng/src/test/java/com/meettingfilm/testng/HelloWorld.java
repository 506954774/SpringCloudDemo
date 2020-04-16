package com.meettingfilm.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by ilinklink on 2020/4/16.
 */
@Slf4j
public class HelloWorld {

    @Test
    public void testA(){
      log.info("this is testA");
    }
    @Test
    public void testB(){
      log.info("this is testB");
    }
    @BeforeSuite
    public void beforeSuite(){
      log.info("this is beforeSuite");
    }
    @AfterSuite
    public void afterSuite(){
      log.info("this is afterSuite");
    }
    @BeforeClass
    public void beforeClass(){
        log.info("this is beforeClass");
    }
    @AfterClass
    public void afterClass(){
        log.info("this is afterClass");
    }
    @BeforeMethod
    public void beforeMethod(){
        log.info("this is beforeMethod");
    }
    @AfterMethod
    public void afterMethod(){
        log.info("this is afterMethod");
    }
    @BeforeTest
    public void beforeTest(){
        log.info("this is beforeTest");
    }
    @AfterTest
    public void afterTest(){
        log.info("this is afterTest");
    }


}
