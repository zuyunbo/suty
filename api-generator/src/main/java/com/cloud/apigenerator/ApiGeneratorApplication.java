package com.cloud.apigenerator;

import com.cloud.apigenerator.config.AutoInterface;
import com.cloud.apigenerator.config.impl.AutoInterfaceImpl;
import com.cloud.apigenerator.service.GenerateCode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGeneratorApplication.class, args);
    }

}
