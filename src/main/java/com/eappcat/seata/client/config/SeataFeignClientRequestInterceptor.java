package com.eappcat.seata.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;


@Configuration
public class SeataFeignClientRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String xid = RootContext.getXID();
        if (!StringUtils.isEmpty(xid)){
            requestTemplate.header(RootContext.KEY_XID,xid);
        }
    }
}
