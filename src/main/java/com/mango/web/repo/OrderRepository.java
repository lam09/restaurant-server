package com.mango.web.repo;

import com.mango.web.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String>,OrderCustomRepository {

    Order findOrderById(String id);
    Order findOrderByTableNo(Integer tableNo);


}