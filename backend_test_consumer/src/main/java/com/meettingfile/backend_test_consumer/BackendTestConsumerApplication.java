package com.meettingfile.backend_test_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
@ServletComponentScan
public class BackendTestConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTestConsumerApplication.class, args);
	}

}
