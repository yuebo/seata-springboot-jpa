package com.eappcat.seata.demo.service;

import com.eappcat.seata.demo.dao.AccountRepository;
import com.eappcat.seata.demo.entity.Account;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @GlobalTransactional
    public void insert(Account account){
        accountRepository.save(account);
        throw new RuntimeException("rollback test");
    }
}
