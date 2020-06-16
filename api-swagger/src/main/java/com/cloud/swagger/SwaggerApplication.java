package com.cloud.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //使用该注解将注册服务到eureka
public class SwaggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }
}
