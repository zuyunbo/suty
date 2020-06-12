package com.example.managebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.cloud.apiservice.mapper"})
public class ManageBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageBackendApplication.class, args);
    }

}
