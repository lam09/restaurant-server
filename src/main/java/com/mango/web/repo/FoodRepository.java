package com.mango.web.repo;


import com.mango.web.entity.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface FoodRepository  extends MongoRepository<Food,String>,FoodCustomRepository
{
    Food findFoodById(String id);
    Food findFoodBySerial(Integer serial);

    @Query("{type:'?0'}")
    List<Food> findFoodsByType(String type);

   // List<Food>getFoodsWithPageAndPageSize(Integer page,Integer pageSize);


}
