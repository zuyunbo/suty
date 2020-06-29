package com.cloud.managebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.cloud.apiservice.mapper"})
//@ComponentScans(value = {@ComponentScan("com.cloud.userauth.configuration"),
//        @ComponentScan("com.cloud.userauth.filters"), @ComponentScan("com.cloud.apiservice"), @ComponentScan("com.cloud.swagger")})
@ComponentScan("com")
@EnableEurekaClient //使用该注解将注册服务到eureka
public class ManageBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageBackendApplication.class, args);
    }

}
