package com.cloud.managebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.cloud.apiservice.mapper"})
@ComponentScan("com")
@EnableEurekaClient
public class ManageBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageBackendApplication.class, args);
    }

}
