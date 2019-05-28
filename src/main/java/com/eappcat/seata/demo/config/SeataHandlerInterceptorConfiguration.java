package com.eappcat.seata.demo.config;

import com.eappcat.seata.demo.core.SeataHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class SeataHandlerInterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SeataHandlerInterceptor()).addPathPatterns("/**");
    }
}
