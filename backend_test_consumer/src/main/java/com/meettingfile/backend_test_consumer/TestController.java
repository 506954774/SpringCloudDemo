package com.meettingfile.backend_test_consumer;

import com.alibaba.fastjson.JSON;
import com.meettingfile.backend_test_consumer.feign.ProviderApi;
import com.meettingfile.backend_test_consumer.feign.ProviderApiRibbon;
import com.meettingfile.backend_test_consumer.feign.SubFeignApi;
import com.meettingfilm.backend.utils.auth.AccessToken;
import com.meettingfilm.backend_common.ResponseEntity;
import com.mettingfilm.api.provider.ProviderSDK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ilinklink on 2020/3/18.
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation(value = "测试eureka调用,使用testTemplate创建httpClient", notes = "测试eureka调用")
    @GetMapping("/getMessage")
    public ResponseEntity testProvider(HttpServletRequest request,@ApiParam(name = "message", value = "信息", required = true)
                                          @RequestParam(value = "message") String message) {

        logHeaders(request);

        ResponseEntity <String> result=new ResponseEntity(true);
        result.setResult(sayHello(message));
        return result ;

    }


    @Resource
    private ProviderApi providerApi;
    @Resource
    private ProviderApiRibbon providerApiRibbon;
    @Resource
    private SubFeignApi subFeignApi;


    @Autowired
    private RestTemplate restTemplate;

    public String sayHello(String message) {
        // 准备工作
//        String hostname = "localhost";
//        int port = 7101;
//        String uri = "/provider/sayhello?message="+message;

        // GET Register
//        ServiceInstance choose = eurekaClient.choose("hello-service-provider");

//        String hostname = choose.getHost();
//        int port = choose.getPort();

        String uri = "/test/getMessage?message="+message;

        // http://localhost:7101/provider/sayhello?message=hello
        String url = "http://helloServiceProvider"+uri;

        // invoker provider test
        String result = restTemplate.getForObject(url, String.class);




        return result;
    }



    @ApiOperation(value = "测试eureka调用生产者的post方法", notes = "测试eureka调用生产者的post方法")
    @GetMapping("/testProviderPost")
    public ResponseEntity testProviderPost( HttpServletRequest request,@ApiParam(name = "randomKey", value = "randomKey", required = true)
                                                @RequestParam("randomKey") String randomKey ,
                                            @ApiParam(name = "token", value = "token", required = true)
                                                @RequestParam("token") String token)
    {

        logHeaders(request);

        ResponseEntity <AccessToken> result=new ResponseEntity(true);
        result.setResult(callProviderPost(randomKey, token));
        return result ;

    }


    public AccessToken callProviderPost(String randomKey,String token) {

        String uri = "/test/auth.do";

        // http://localhost:7101/provider/sayhello?message=hello
        String url = "http://helloServiceProvider"+uri;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("randomKey", randomKey);
        map.add("token", token);

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



    @ApiOperation(value = "测试feign调用生产者的post方法,不走Ribbon,目标url写死", notes = "测试feign调用生产者的post方法,不走rRibbon,目标url写死")
    @PostMapping("/testFeignProviderPost")
    public ResponseEntity testFeignProviderPost(  HttpServletRequest request,@RequestBody AccessToken params)
    {
        logHeaders(request);

        return providerApi.providerPost(params) ;

    }


    @ApiOperation(value = "测试feign调用生产者的post方法,集成Ribbon，负载均衡", notes = "测试feign调用生产者的post方法,集成Ribbon，负载均衡")
    @PostMapping("/testFeignRibbonProviderPost")
    public ResponseEntity testFeignRibbonProviderPost(  HttpServletRequest request,@RequestBody AccessToken params)
    {
        logHeaders(request);

        return providerApiRibbon.providerPost(params) ;

    }

    @ApiOperation(value = "测试Feign:继承生产者提供的feign接口 ", notes = "测试继承生产者提供的feign接口")
    @PostMapping("/testSubFeignRibbonProviderPost")
    public ResponseEntity testSubFeignRibbonProviderPost( HttpServletRequest request, @RequestBody AccessToken params)
    {

        logHeaders(request);
        return subFeignApi.providerPost(params) ;
    }

    private void logHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headName = headerNames.nextElement();
            log.error("describeFilms - headName:{}, headValue:{}", headName, request.getHeader(headName));
        }
    }

}
