package com.meettingfilm.zuul.config;

import com.meettingfilm.zuul.filters.MyFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.apigwzuul.config
 * @description :
 **/
@Configuration
public class ZuulConfig {

    @Bean
    public MyFilter initMyFilter(){
        return new MyFilter();
    }

}