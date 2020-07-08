package com.meettingfilm.backend.backend_gateway.conf;

import com.meettingfilm.backend.backend_gateway.filters.CorsGlobalFilter;
import com.meettingfilm.backend.backend_gateway.filters.JwtGlobalFilter;
import com.meettingfilm.backend.backend_gateway.filters.LogGlobalFilter;
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
    public LogGlobalFilter initMyGlobalFilter(){
        return new LogGlobalFilter();
    }
    @Bean
    public CorsGlobalFilter initCorsGlobalFilter(){
        return new CorsGlobalFilter();
    }
    @Bean
    public JwtGlobalFilter initJwtGlobalFilter(){
        return new JwtGlobalFilter();
    }

}
