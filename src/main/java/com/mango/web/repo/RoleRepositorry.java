package com.mango.web.repo;

import com.mango.web.entity.Account;
import com.mango.web.entity.Role;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositorry  extends MongoRepository<Role,Integer> {
    Role findRoleByName(String name);
}
