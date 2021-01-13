package com.meettingfile.backend_test_consumer.feign;

import com.meettingfilm.backend.utils.auth.AccessToken;
import com.meettingfilm.backend_common.ResponseEntity;
import com.mettingfilm.api.provider.ProviderSDK;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ilinklink on 2020/4/13.
 */
@Service
public class FallBackImpl2 implements SubFeignApi {


    @Override
    public ResponseEntity providerPost(@RequestBody AccessToken p) {

        AccessToken params=new AccessToken();
        params.setToken("消费者提供的服务降级：fallBack");
        params.setRandomKey("消费者提供的服务降级：fallBack");
        params.setPort(-1);
        ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
        responseEntity.setResult(params);
        return responseEntity;

    }

    //@Override
    public ResponseEntity uploadFile(HttpServletRequest request) {
        AccessToken params=new AccessToken();
        params.setToken("消费者提供的服务降级：fallBack");
        params.setRandomKey("消费者提供的服务降级：fallBack");
        params.setPort(-1);
        ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
        responseEntity.setResult(params);
        return responseEntity;
    }

    //@Override
    public ResponseEntity multiImport(HttpServletRequest request) {
        AccessToken params=new AccessToken();
        params.setToken("消费者提供的服务降级：fallBack");
        params.setRandomKey("消费者提供的服务降级：fallBack");
        params.setPort(-1);
        ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
        responseEntity.setResult(params);
        return responseEntity;
    }

    @Override
    public ResponseEntity uploadFileAction2(MultipartFile file) {
        AccessToken params=new AccessToken();
        params.setToken("消费者提供的单文件上传，服务降级：fallBack");
        params.setRandomKey("消费者提供的单文件上传，服务降级：fallBack");
        params.setPort(-1);
        ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
        responseEntity.setResult(params);
        return responseEntity;
    }

    @Override
    public ResponseEntity multiImportAction2(MultipartFile[] files) {
        AccessToken params=new AccessToken();
        params.setToken("消费者提供的多文件上传，服务降级：fallBack");
        params.setRandomKey("消费者提供的多文件上传，服务降级：fallBack");
        params.setPort(-1);
        ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
        responseEntity.setResult(params);
        return responseEntity;
    }
}
