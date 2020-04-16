package com.meettingfilm.testng.zuultest;

import com.meettingfilm.backend.utils.auth.AccessToken;
import com.meettingfilm.backend_common.ResponseEntity;
import com.meettingfilm.backend_common.entity.LoginParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by ilinklink on 2020/4/16.
 */
@Slf4j
public class ZuulTest {


    private String mToken;//每次开始时，执行登录，然后保存token


    @Autowired
    private RestTemplate restTemplate;

    @Test(dataProvider = "filmsDataProvider")
    public void callAuth(String token,boolean loginResult){
      log.info("this is login");

        AccessToken params=new AccessToken();
        params.setRandomKey("a8n3ue");
        params.setToken(token);
        //params.setToken("eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJhOG4zdWUiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTU4NzU0OTYwNywiaWF0IjoxNTg2OTQ0ODA3fQ.DyFt9aVNRkjLn1w2IoXdbxq48zS-cXbdAO6bIf0lt8SA-3A5mLED21-b-pEPhBIjwGTCO18LsY_l18NF609x7w");
        params.setUserId("d");
        params.setPort(0);



        AccessToken  result =callProviderPost(params.getRandomKey(),params.getToken());

        if(result==null){
            result=new AccessToken();
            result.setToken("error");
        }
        Assert.assertEquals(result.getToken().length() > 20, loginResult);

    }

    @DataProvider(name = "filmsDataProvider")
    public Object[][] filmsDataProvider(){
        Object[][] objects = new Object[][]{
                {"1eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJhOG4zdWUiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTU4NzU0OTYwNywiaWF0IjoxNTg2OTQ0ODA3fQ.DyFt9aVNRkjLn1w2IoXdbxq48zS-cXbdAO6bIf0lt8SA-3A5mLED21-b-pEPhBIjwGTCO18LsY_l18NF609x7w", true},
                {"eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJhOG4zdWUiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTU4NzU0OTYwNywiaWF0IjoxNTg2OTQ0ODA3fQ.DyFt9aVNRkjLn1w2IoXdbxq48zS-cXbdAO6bIf0lt8SA-3A5mLED21-b-pEPhBIjwGTCO18LsY_l18NF609x7w", true}
        };

        return objects;
    }

    public AccessToken callProviderPost(String randomKey,String token) {

        restTemplate =new RestTemplate();

        // http://localhost:7101/provider/sayhello?message=hello
        String url = "http://localhost:10000/backend/consumer/test/testSubFeignRibbonProviderPost";


        HttpHeaders headers = new HttpHeaders();


        log.info("===================================================================================callProviderPost，成员变量token："+ mToken);

        headers.add("Authorization",mToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("randomKey", randomKey);
        map.add("mToken", token);

        AccessToken accessToken=new AccessToken();
        accessToken.setRandomKey(randomKey);
        accessToken.setToken(token);

        //HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        //HttpEntity<String> request = new HttpEntity<>(JSON.toJSONString(accessToken), headers);

        HttpEntity<AccessToken> request = new HttpEntity<>(accessToken, headers);
        org.springframework.http.ResponseEntity<ResponseEntityAccessToken > accessTokenResponseEntity = restTemplate.postForEntity(url, request,ResponseEntityAccessToken.class);



        return    accessTokenResponseEntity.getBody().getResult();
    }

    public static class ResponseEntityAccessToken extends ResponseEntity<AccessToken>{
        public ResponseEntityAccessToken( ) {
            super(true);
        }


        public ResponseEntityAccessToken(boolean success) {
            super(success);
        }

        public ResponseEntityAccessToken(String code, boolean success, String message) {
            super(code, success, message);
        }
    }


    @DataProvider(name = "accountProvider")
    public Object[][] account(){
        Object[][] objects = new Object[][]{
                {"admin","admin" ,true},
                {"chuck","admin", true}
        };

        return objects;
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


        mToken =getToken("admin","admin");

        log.info("获取到的token："+ mToken);
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



    public String getToken(String userName,String password) {

        restTemplate =new RestTemplate();

        String url = "http://localhost:10000/backend/consumer/test/auth/login";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        LoginParams loginParams=new LoginParams();
        loginParams.setUserName(userName);
        loginParams.setPassword(password);


        HttpEntity<LoginParams> request = new HttpEntity<>(loginParams, headers);
        org.springframework.http.ResponseEntity<ResponseEntityAccessToken > accessTokenResponseEntity = restTemplate.postForEntity(url, request,ResponseEntityAccessToken.class);

        return   accessTokenResponseEntity.getBody().getResult().getToken();
    }


}
