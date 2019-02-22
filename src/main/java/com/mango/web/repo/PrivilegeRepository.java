package com.mango.web.repo;

import com.mango.web.entity.Account;
import com.mango.web.entity.Privilege;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrivilegeRepository  extends MongoRepository<Privilege,String>,PrivilegeCustomRepository {
    List<Privilege> findAllByAccount(Account account);

}
