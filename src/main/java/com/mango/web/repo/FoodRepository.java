package com.mango.web.repo;


import com.mango.web.entity.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



public interface FoodRepository  extends MongoRepository<Food,String>
{
    Food findFoodById(String id);
    Food findFoodBySerial(Integer serial);
}
