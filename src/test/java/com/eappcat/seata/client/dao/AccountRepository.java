package com.eappcat.seata.client.dao;

import com.eappcat.seata.client.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
