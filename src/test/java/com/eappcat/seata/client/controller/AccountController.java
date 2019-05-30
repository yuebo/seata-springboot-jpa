package com.eappcat.seata.client.controller;

import com.eappcat.seata.client.entity.Account;
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
    public String index(){
        Account account=new Account();
        account.setName("tx1_"+System.currentTimeMillis());
        accountService.insert(account);
        String result=testClient.test();
        return result;
    }

    @GetMapping("/test")
    @GlobalTransactional
    public String test(){
        Account account=new Account();
        account.setName("tx2_"+System.currentTimeMillis());
        accountService.insert(account);
        return "ok";
    }
}
