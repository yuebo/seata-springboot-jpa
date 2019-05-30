package com.eappcat.seata.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories
@EnableFeignClients
public class SeataStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataStarterApplication.class, args);
    }
}
