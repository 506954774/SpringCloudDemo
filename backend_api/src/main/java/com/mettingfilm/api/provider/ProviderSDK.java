package com.mettingfilm.api.provider;

import com.meettingfilm.backend.utils.auth.AccessToken;
import com.meettingfilm.backend_common.ResponseEntity;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilinklink on 2020/4/13.
 */
//每个微服务需要提供一个接口,把自己提供对外的方法公布出去.每个微服务的开发者必须维护此接口与自己代码里业务接口的一致性
//直接在依赖库里加注解，无效，消费者拿不到这个bean
    //不用给他加降级，生产者逻辑层应该有服务降级
public interface ProviderSDK {

    final String SERVICE_ID="provider";

    //注意，不可使用 getMapping 或者 postMapping
    @RequestMapping(value = "/test/auth.do",method = RequestMethod.POST)
    ResponseEntity providerPost(
            @RequestBody AccessToken params);

    //Feign不支持文件上传
/*    @RequestMapping(value ="/test/upload",method = RequestMethod.POST)
    ResponseEntity uploadFile(HttpServletRequest request) ;


    @RequestMapping(value ="/test/multi_upload",method = RequestMethod.POST)
    ResponseEntity multiImport(HttpServletRequest request) ;*/
}
