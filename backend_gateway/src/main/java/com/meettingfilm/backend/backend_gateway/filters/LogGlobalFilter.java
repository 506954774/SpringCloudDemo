package com.meettingfilm.backend.backend_gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import reactor.core.publisher.Mono;

/**
 * @author : jiangzh
 * @program : com.springcloud.gatewaydemo.Filters
 * @description : 自定义全局Filter
 **/
@Slf4j
public class LogGlobalFilter implements GlobalFilter, Ordered {

  private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  /**
  * @Description: 业务处理逻辑
  * @Param: [exchange, chain]
  * @return: reactor.core.publisher.Mono<java.lang.Void>
  * @Author: jiangzh
  */
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    long beginTime = System.currentTimeMillis();
    //log.info("filter start ----> beginTime:{}", beginTime);
    return chain.filter(exchange).then(Mono.fromRunnable(()->{
      long endTime = System.currentTimeMillis();

      String uri= null;
      try {
        uri = exchange.getRequest().getURI().getPath();
      } catch (Exception e) {
        e.printStackTrace();
      }
      InetSocketAddress remoteAddress = null;
      try {
        remoteAddress = exchange.getRequest().getRemoteAddress();
      } catch (Exception e) {
        e.printStackTrace();
      }
      String clientIp = null;
      try {
        clientIp = Objects.requireNonNull(remoteAddress).getAddress().getHostAddress();
      } catch (Exception e) {
        e.printStackTrace();
      }
      String time= null;
      try {
        time = simpleDateFormat.format(new Date(beginTime));
      } catch (Exception e) {
        e.printStackTrace();
      }
      log.info("请求耗时记录： ----> uri: [{}] ， 时间: [{}] , 耗时: [{}] 毫秒 ,ip地址:[{}]",uri,time , endTime-beginTime,clientIp);
    }));
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
    return 0;
  }
}
