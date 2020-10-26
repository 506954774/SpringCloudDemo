package com.meettingfilm.backend_test_provider;

import com.meettingfilm.backend_test_provider.upload.FastDFSClient;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCircuitBreaker
@EnableHystrix
//@EnableHystrixDashboard
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
@ServletComponentScan
@Slf4j
public class BackendTestProviderApplication {

	private static final Logger logger = Logger.getLogger(BackendTestProviderApplication.class);


	@Value("${dfs.client.conf}")
	private String dfsClient;

	@Value("${dfs.client.conf}")
	private String dfsClientPath;

	public static void main(String[] args) {
		SpringApplication.run(BackendTestProviderApplication.class, args);
	}

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
	}


	@Bean
	FastDFSClient fastDFSClient() {
		logger.info(  "FastDFSClient配置文件[" + dfsClientPath + "].");
		try {
			return new FastDFSClient(dfsClientPath);
		} catch (Exception e) {
			logger.error(  "FastDFSClient实例化失败！", e);
			return null;
		}
	}
}
