package com.eappcat.seata.client.controller;

import com.eappcat.seata.client.feign.TestClient;
import com.eappcat.seata.client.service.AccountService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TestClient testClient;
    @GetMapping
    @GlobalTransactional
    public String test(){
        String baidu=testClient.baidu();
//        Account account=new Account();
//        account.setName("name_"+System.currentTimeMillis());
//        accountService.insert(account);
        return baidu;
    }
}
