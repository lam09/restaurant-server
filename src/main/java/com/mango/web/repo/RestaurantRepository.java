package com.mango.web.repo;

import com.mango.web.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository  extends MongoRepository<Restaurant,String> {
}
