package com.cloud.globalexception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GlobalExceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlobalExceptionApplication.class, args);
    }

}
