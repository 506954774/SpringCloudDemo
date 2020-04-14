package com.meettingfile.backend_test_consumer.feign;

import com.meettingfilm.backend.utils.auth.AccessToken;
import com.meettingfilm.backend_common.ResponseEntity;
import com.mettingfilm.api.provider.ProviderSDK;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by ilinklink on 2020/4/13.
 */
@Service
public class FallBackImpl2 implements SubFeignApi {


    @Override
    public ResponseEntity providerPost(@RequestBody AccessToken p) {

        AccessToken params=new AccessToken();
        params.setToken("FallBackImpl2：fallBack");
        params.setRandomKey("FallBackImpl2：fallBack");
        params.setPort(-1);
        ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
        responseEntity.setResult(params);
        return responseEntity;

    }
}
