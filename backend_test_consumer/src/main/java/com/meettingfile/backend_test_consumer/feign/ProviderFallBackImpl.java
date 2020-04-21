package com.meettingfile.backend_test_consumer.feign;

import com.meettingfilm.backend.utils.auth.AccessToken;
import com.meettingfilm.backend_common.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by ilinklink on 2020/4/13.
 */
@Service
public class ProviderFallBackImpl implements ProviderApiRibbon {


    @Override
    public ResponseEntity providerPost(@RequestBody AccessToken p) {

        AccessToken params=new AccessToken();
        params.setToken("服务调用者提供的降级：fallBack");
        params.setRandomKey("服务调用者提供的降级：fallBack");
        params.setPort(-1);
        ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
        responseEntity.setResult(params);
        return responseEntity;

    }
}
