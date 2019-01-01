package com.mango.web.repo;

import com.mango.web.entity.Food;

import java.util.List;

public interface FoodCustomRepository  {
    public List<Food> selectCustomFoods(Integer page,Integer pageSize);
}
