package com.laowengs.shardingsphere.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.laowengs.shardingsphere.demo.* ")
public class ShardingsphereDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingsphereDemoApplication.class, args);
	}

}
