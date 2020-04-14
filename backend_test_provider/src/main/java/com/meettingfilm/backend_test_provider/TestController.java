package com.meettingfilm.backend_test_provider;

import com.meettingfilm.backend.utils.JwtProperties;
import com.meettingfilm.backend.utils.JwtTokenUtil;
import com.meettingfilm.backend.utils.auth.AccessToken;
import com.meettingfilm.backend_common.ResponseEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.jsonwebtoken.JwtException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ilinklink on 2020/3/18.
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController extends ServerExceptionHandler {

    @Value("${server.port}")
    private int port;


    /*
    fallback是业务处理的保底方案，尽可能保证这个保底方案一定能成功
 */
    public ResponseEntity fallbackMethod( String message) throws AdminException{
        /*
            打发票， -》 没打印纸了， 换台机器或者下次再试
            -》 触发告警 -》 告知运维人员，打印发票业务挂了
         */
        // describeCinemas -》 获取影院列表 -> 在Redis中查询影院列表[redis挂了，或者数据没了] -> 获取超时

        // fallback里捕获到超时或者数据内容与分页数不一致

        // fallback就在数据库中查询真实的影院信息

        // 返回一定是成功，或者业务处理失败
        ResponseEntity <String> result=new ResponseEntity(true);
        result.setResult("fallbackMethod！ provider:"+port+","+message);
        return result ;
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "1000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
            } ,ignoreExceptions = AdminException.class)
    //,ignoreExceptions = AdminException.class
    @ApiOperation(value = "测试eureka调用", notes = "测试eureka调用")
    @GetMapping("/getMessage")
    public ResponseEntity testProvider(@ApiParam(name = "message", value = "信息", required = true)
                                          @RequestParam(value = "message") String message) throws AdminException {

        if(message.startsWith("error")){
            throw  new AdminException("error","数据不合法，业务异常！");
        }
        else if(message.startsWith("sleep")){
            // 服务降级:模拟超时导致降级
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {

            }
        }

        ResponseEntity <String> result=new ResponseEntity(true);
        result.setResult("this is from provider:"+port+","+message);
        return result ;

    }


    @ApiOperation(value="测试post请求", response = Void.class, notes="测试post请求")
    @PostMapping("/auth.do")
    public ResponseEntity auth(@RequestBody AccessToken params) {

        log.info("生产者接收到的参数:" + params);

        JwtTokenUtil util=new JwtTokenUtil();

        if(params==null){

            params=new AccessToken();
            params.setToken("参数为空！");
            params.setRandomKey("null");
            params.setPort(port);
            ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(params);
            return responseEntity;
        }

        try {
            util.parseToken(params.getToken());
        } catch (JwtException e) {
            params=new AccessToken();
            params.setToken("不合法的token");
            params.setRandomKey("null");
            params.setPort(port);

            ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(params);
            return responseEntity;
        }

        Date expirationDateFromToken = util.getExpirationDateFromToken(params.getToken());

        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        params=new AccessToken();
        params.setToken("token 过期时间："+format.format(expirationDateFromToken));
        params.setRandomKey("null");
        params.setPort(port);

        ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
        responseEntity.setResult(params);
        return responseEntity;
    }

}
