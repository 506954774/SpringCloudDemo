package com.meettingfilm.backend_test_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
@ServletComponentScan
public class BackendTestProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTestProviderApplication.class, args);
	}

}
