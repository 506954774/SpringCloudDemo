package com.meettingfilm.backend.backend_gateway.filters;

import com.alibaba.fastjson.JSONObject;
import com.meettingfilm.backend.backend_gateway.model.ResponseEntity;
import com.meettingfilm.backend.utils.JwtProperties;
import com.meettingfilm.backend.utils.JwtTokenUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : jiangzh
 * @program : com.springcloud.gatewaydemo.Filters
 * @description : 自定义全局Filter
 **/
@Slf4j
public class JwtGlobalFilter implements GlobalFilter, Ordered {

    private ResponseEntity mNologin = new ResponseEntity("500", false, "请重新登录！");
    private ResponseEntity mUnknown = new ResponseEntity("500", false, "服务器异常！");


    /**
     * @Description: 判断Filter顺序的
     * @Param: []
     * @return: int
     * @Author: jiangzh
     */
    @Override
    public int getOrder() {
        return 2;
    }




    private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, Object jsonObject) {
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSONObject.toJSONString(jsonObject).getBytes());
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        String uri = serverHttpRequest.getURI().getPath();
        // JWT工具类
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        JwtProperties jwtProperties = JwtProperties.getJwtProperties();

        //  检查白名单（配置）
        if (uri.endsWith("/" + jwtProperties.getAuthPath())) {
            return chain.filter(exchange);
        }

        String token = serverHttpRequest.getHeaders().getFirst(jwtProperties.getHeader());
        if (StringUtils.isBlank(token)) {
            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            return getVoidMono(serverHttpResponse, mNologin);
        }

        String userId = null;
        try {

            boolean flag = jwtTokenUtil.isTokenExpired(token);
            if (flag) {
                return getVoidMono(serverHttpResponse, mNologin);
            } else {
                // 2、解析出JWT中的payload -> userid - randomkey
                String randomkey = jwtTokenUtil.getMd5KeyFromToken(token);
                userId = jwtTokenUtil.getuserIdfromtoken(token);
                // 3、是否需要验签,以及验签的算法

                // 4、判断userid是否有效
                // TODO
            }


        } catch (Exception ex) {
            return getVoidMono(serverHttpResponse, mUnknown);
        }


        ServerHttpRequest mutableReq = serverHttpRequest.mutate().header("userId", userId).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

}
