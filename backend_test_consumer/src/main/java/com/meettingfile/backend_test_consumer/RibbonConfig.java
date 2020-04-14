package com.meettingfile.backend_test_consumer;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ilinklink on 2020/3/31.
 */
@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule iRule(){
         //return new WeightedResponseTimeRule();
        return new RoundRobinRule();
       // return new AvailabilityFilteringRule();
       // return new BestAvailableRule();
    }
}
