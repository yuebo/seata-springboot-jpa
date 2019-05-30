package com.eappcat.seata.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "spring.seata")
@Data
public class SeataClientConfigProperties {
    private String applicationId="app";
    private String groupId="default";
    private List<String> proxyList=new ArrayList<>();
}
