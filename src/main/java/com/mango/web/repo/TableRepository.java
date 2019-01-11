package com.mango.web.repo;

import com.mango.web.entity.Table;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TableRepository extends MongoRepository<Table, String> {
    Table findTableByTableNo(Integer tableNo);
}
