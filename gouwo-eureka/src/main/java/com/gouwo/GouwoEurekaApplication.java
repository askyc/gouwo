package com.gouwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GouwoEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GouwoEurekaApplication.class, args);
    }

}
