package com.mango.web.repo;

import com.mango.web.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String>,OrderCustomRepository {

    Order findOrderById(String id);
    Order findOrderByTableNo(Integer tableNo);


}