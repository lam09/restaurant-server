package com.mango.web.repo;

import com.mango.web.entity.Account;
import com.mango.web.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RestaurantRepository  extends MongoRepository<Restaurant,String> {
    Restaurant findRestaurantByAdmin(Account admin);
    Restaurant findRestaurantById(String id);
}
