package com.mango.web.repo;

import com.mango.web.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository// extends PagingAndSortingRepository<Food,Integer>
{

//    @Query(value = "select * from food",nativeQuery = true)
//    List<Food> findFoodList();

  /*  @Query(value = "select * from food where type LIKE :foodType",nativeQuery = true)
    List<Food> findFoodListByType(@Param("foodType") String foodType);

    //select pageable
    @Query(value = "select * from (select )",nativeQuery = true)
    List<Food> findFoodsPanigation(Integer pageIndex,Integer pageCount);


    @Query(value = "Select * from food ",nativeQuery = true)
    Page<Food> findFoodPagination(Pageable pageable);
*/
}
