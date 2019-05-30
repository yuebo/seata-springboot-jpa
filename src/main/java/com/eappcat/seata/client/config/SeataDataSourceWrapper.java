package com.eappcat.seata.client.config;

import com.eappcat.seata.client.SeataClientConfigProperties;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SeataDataSourceWrapper implements BeanPostProcessor {

    @Autowired
    private SeataClientConfigProperties clientConfigProperties;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        if(bean instanceof DataSource){
            if(!(bean instanceof DataSourceProxy)&&(clientConfigProperties.getProxyList()==null||clientConfigProperties.getProxyList().size()==0||clientConfigProperties.getProxyList().contains(s))){
                return new DataSourceProxy((DataSource)bean);
            }
        }
        return bean;
    }
}
