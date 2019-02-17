package com.mango.web.repo;

import com.mango.web.entity.Privilege;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrivilegeRepository  extends MongoRepository<Privilege,String> {
}
