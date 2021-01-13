package feign;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * FeignSupportConfig
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/8  11:43
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Configuration
public class FeignSupportConfig {


    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    /**
     * @Description // 此处注入的是： ObjectFactory<HttpMessageConverters>
     * @Date 23:04 2020/4/9
     * @Param []
     * @return feign.form.spring.SpringFormEncoder
     **/
    @Bean
    public Encoder feignEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
       // return new FeignSpringFormEncoder();
    }


}
