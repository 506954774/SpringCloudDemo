package com.meettingfilm.zuul.fallBack;


import com.alibaba.fastjson.JSONObject;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.meettingfilm.backend_common.ResponseEntity;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.apigwzuul
 * @description : 业务降级处理
 **/
// @Component
    //一般不会在网关里做服务降级
public class MyFallback implements FallbackProvider {

    /**
     * @Description: 针对哪一个路由进行降级， return可以写 *
     * @Param: []
     * @return: java.lang.String
     * @Author: jiangzh
     */
    @Override
    public String getRoute() {
        return "helloServiceConsumer";
    }

    /**
     * @Description: 降级时处理方式
     * @Param: [route, cause]
     * @return: org.springframework.http.client.ClientHttpResponse
     * @Author: jiangzh
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            /**
             * @Description: 业务降级处理方式
             * @Param: []
             * @return: java.io.InputStream
             * @Author: jiangzh
             */
            @Override
            public InputStream getBody() throws IOException {
                ResponseEntity responseVO
                        =
                        new ResponseEntity("500",false, "zuul fallback!~");
                String result = JSONObject.toJSONString(responseVO);
                return new ByteArrayInputStream(result.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;

                //testsubfeignribbonproviderpost
            }
        };
    }
}
