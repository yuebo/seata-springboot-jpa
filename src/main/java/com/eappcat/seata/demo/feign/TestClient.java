package com.eappcat.seata.demo.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://www.baidu.com",name = "test")
public interface TestClient {
    @GetMapping
    String baidu();
}
