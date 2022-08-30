package com.zyf.redenvelopesystem.async;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.zyf.redenvelopesystem.common.redis","com.zyf.redenvelopesystem.async"})
@MapperScan(basePackages = {"com.zyf.redenvelopesystem.common.database.dao"})
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class,args);
    }
}
