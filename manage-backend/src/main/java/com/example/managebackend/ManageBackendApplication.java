package com.example.managebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.cloud.apiservice.mapper"})
@ComponentScan("com")
@EnableEurekaClient //使用该注解将注册服务到eureka
public class ManageBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageBackendApplication.class, args);
    }

}
