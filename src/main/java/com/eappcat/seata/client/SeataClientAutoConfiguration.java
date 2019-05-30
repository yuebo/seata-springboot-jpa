package com.eappcat.seata.client;

import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SeataClientConfigProperties.class)
@ComponentScan
public class SeataClientAutoConfiguration {
    @Autowired
    private SeataClientConfigProperties clientConfigProperties;
    @Bean
    GlobalTransactionScanner globalTransactionScanner(){
        return new GlobalTransactionScanner(clientConfigProperties.getApplicationId(),clientConfigProperties.getGroupId());
    }

}
