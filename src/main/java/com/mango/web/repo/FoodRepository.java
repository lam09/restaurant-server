package com.mango.web.repo;

import com.mango.web.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food,Integer> {

    @Query(value = "select * from food",nativeQuery = true)
    List<Food> findFoodList();

    @Query(value = "select * from food where type LIKE :foodType",nativeQuery = true)
    List<Food> findFoodListByType(@Param("foodType") String foodType);


}
