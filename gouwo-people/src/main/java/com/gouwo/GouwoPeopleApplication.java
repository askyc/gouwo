package com.gouwo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gouwo.mapper")
public class GouwoPeopleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GouwoPeopleApplication.class, args);
    }

}
