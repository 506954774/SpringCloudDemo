package com.meettingfilm.backend.backend_gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.gw.controller
 * @description :
 **/
@Slf4j
@RestController("/test")
public class HelloController {

    /**
    * @Description:  serverWebExchange - 请求对象，线程安全
     *              throwable - 具体的错误信息
    * @Param: [serverWebExchange, throwable]
    * @return: com.mooc.meetingfilm.utils.common.vo.BaseResponseVO
    * @Author: jiangzh
    */
    @GetMapping(value = "/myFallback")
    public ResponseEntity demoFallback(
                         ServerWebExchange serverWebExchange, Throwable throwable){

        Set<String> keys = serverWebExchange.getRequest().getQueryParams().keySet();
        for(String key : keys){
            log.info("key:{} , value:{}", key, serverWebExchange.getRequest().getHeaders().get(key));
        }


        if(throwable !=null){
            log.error("throwable: [{}]", throwable.getMessage());
//            throwable.printStackTrace();
        }

        return new ResponseEntity( "演示请求失败，降级处理！", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/test")
    public ResponseEntity test(){
        return new ResponseEntity( "test！", HttpStatus.OK);
    }

}
