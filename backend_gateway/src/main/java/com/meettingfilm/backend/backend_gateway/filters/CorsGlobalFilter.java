package com.meettingfilm.backend.backend_gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author : jiangzh
 * @program : com.springcloud.gatewaydemo.Filters
 * @description : 自定义全局Filter
 **/
@Slf4j
public class CorsGlobalFilter implements GlobalFilter, Ordered {

  private static final String MAX_AGE = "18000L";


  /**
   * 解决跨域问题
   * @param ctx
   * @param chain
   * @return
   */
  @Override
  public Mono<Void> filter(ServerWebExchange ctx, GatewayFilterChain chain) {
    ServerHttpRequest request = ctx.getRequest();
    if (CorsUtils.isCorsRequest(request)) {
      HttpHeaders requestHeaders = request.getHeaders();
      ServerHttpResponse response = ctx.getResponse();
      HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
      HttpHeaders headers = response.getHeaders();
      headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
      headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
      headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
      headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
      headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
      if (request.getMethod() == HttpMethod.OPTIONS) {
        response.setStatusCode(HttpStatus.OK);
        return Mono.empty();
      }
    }
    return chain.filter(ctx);
  }

  /*
      request -> f1 -> f2 -> f3 -> service
                      ... f2   <-  f3 <-
   */

  /**
  * @Description: 判断Filter顺序的
  * @Param: []
  * @return: int
  * @Author: jiangzh
  */
  @Override
  public int getOrder() {
    return 1;
  }
}
