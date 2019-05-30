package com.eappcat.seata.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8080/test",name = "test")
public interface TestClient {
    @GetMapping
    String test();
}
