package com.mango.web.repo;

import com.mango.web.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account,String> {
    Optional<Account> findAccountByEmail(String email);
    Optional<Account> findAccountByUsername(String username);
}

