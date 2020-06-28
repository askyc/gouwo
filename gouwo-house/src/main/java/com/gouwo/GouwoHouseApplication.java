package com.gouwo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Askyc
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.gouwo.mapper")
public class GouwoHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(GouwoHouseApplication.class, args);
	}

}
