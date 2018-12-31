package com.mango.web.repo;

import com.mango.web.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account,Integer> {
    Account findAccountByEmail(String email);
}

