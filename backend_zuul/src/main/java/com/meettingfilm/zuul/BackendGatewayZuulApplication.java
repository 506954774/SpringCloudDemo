package com.meettingfilm.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class BackendGatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendGatewayZuulApplication.class, args);
	}

}
