package com.zyf.redenvelopesystem.query;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.zyf.redenvelopesystem.common.database.dao"})
@ComponentScan({"com.zyf.redenvelopesystem.common.redis","com.zyf.redenvelopesystem.query"})
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class,args);
    }
}
