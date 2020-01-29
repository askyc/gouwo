package com.gouwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GouwoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GouwoGatewayApplication.class, args);
    }

}
