package com.eappcat.seata.demo.config;

import com.eappcat.seata.demo.core.SeataRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class SeataRestTemplateConfiguration {
    @Bean
    public SeataRestTemplateInterceptor seataRestTemplateInterceptor() {
        return new SeataRestTemplateInterceptor();
    }

    @Autowired(required = false)
    private Collection<RestTemplate> restTemplates;

    @Autowired
    private SeataRestTemplateInterceptor seataRestTemplateInterceptor;

    @PostConstruct
    public void init() {
        if (this.restTemplates != null) {
            for (RestTemplate restTemplate : restTemplates) {
                List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(
                        restTemplate.getInterceptors());
                interceptors.add(this.seataRestTemplateInterceptor);
                restTemplate.setInterceptors(interceptors);
            }
        }
    }
}
