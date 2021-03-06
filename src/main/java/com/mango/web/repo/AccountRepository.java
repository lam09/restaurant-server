package com.mango.web.repo;

import com.mango.web.entity.Account;
import com.mango.web.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account,String> {
    Optional<Account> findAccountByEmail(String email);
    Optional<Account> findAccountByUsername(String username);
    boolean existsAccountByEmail(String email);

}

