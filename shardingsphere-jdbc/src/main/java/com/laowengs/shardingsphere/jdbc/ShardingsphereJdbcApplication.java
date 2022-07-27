package com.laowengs.shardingsphere.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.laowengs.*")
@EntityScan(basePackages = "com.laowengs.shardingsphere.core.entity")
public class ShardingsphereJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereJdbcApplication.class, args);
    }

}
