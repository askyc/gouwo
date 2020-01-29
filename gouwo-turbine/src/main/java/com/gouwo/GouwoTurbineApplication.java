package com.gouwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableHystrixDashboard//启用Hystrix Dashboard
@EnableTurbine//启用Turbine
@SpringBootApplication
public class GouwoTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(GouwoTurbineApplication.class, args);
    }

}
