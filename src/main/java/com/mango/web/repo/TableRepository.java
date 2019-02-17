package com.mango.web.repo;

import com.mango.web.entity.OrderLocation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TableRepository extends MongoRepository<OrderLocation, String> {
    OrderLocation findOrderLocationByOrderLocationNo(Integer number);
}
