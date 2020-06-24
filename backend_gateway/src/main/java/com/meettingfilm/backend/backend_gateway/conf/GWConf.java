package com.meettingfilm.backend.backend_gateway.conf;

import com.meettingfilm.backend.backend_gateway.filters.MyGlobalFilter;
import com.meettingfilm.backend.backend_gateway.predicates.JiangzhAfterRoutePredicateFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.gw.conf
 * @description : SpringCloud Gateway配置文件
 **/
@Configuration
public class GWConf {

    @Bean
    public JiangzhAfterRoutePredicateFactory initJiangzhAfterRoutePredicateFactory(){
        return new JiangzhAfterRoutePredicateFactory();
    }

    @Bean
    public MyGlobalFilter initMyGlobalFilter(){
        return new MyGlobalFilter();
    }

}
