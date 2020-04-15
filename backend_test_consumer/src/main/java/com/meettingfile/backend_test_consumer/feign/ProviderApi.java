package com.meettingfile.backend_test_consumer.feign;

import com.meettingfilm.backend.utils.auth.AccessToken;
import com.meettingfilm.backend_common.ResponseEntity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ilinklink on 2020/4/13.
 */
@FeignClient(name = "provider1",
        path = "/test",
        url = "http://localhost:9201"
       // configuration = FeignConfig.class

        //       fallbackFactory = FallbackFactory.class
//        fallback = ProviderFallbackAPIImpl.class
//        configuration = FeignHelloConf.class,
//        url = "http://localhost:7101"
)
public interface ProviderApi {

    //注意，不可使用 getMapping 或者 postMapping
       @RequestMapping(value = "/auth.do",method = RequestMethod.POST)
       ResponseEntity  providerPost(
            @RequestBody AccessToken params);
}
