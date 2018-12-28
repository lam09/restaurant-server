package com.mango.web.repo;

import com.mango.web.entity.Account;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account,Integer> {
    Account findAccountByUsername(String username);
}

