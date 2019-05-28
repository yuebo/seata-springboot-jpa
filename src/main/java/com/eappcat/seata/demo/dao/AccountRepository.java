package com.eappcat.seata.demo.dao;

import com.eappcat.seata.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
