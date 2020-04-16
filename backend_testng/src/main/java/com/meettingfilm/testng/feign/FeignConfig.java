package com.meettingfilm.testng.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;

/**
 * Created by ilinklink on 2020/4/13.
 */
@Configuration
public class FeignConfig {
    @Bean
    public Contract contract(){
        return new Contract.Default();
    }
}
