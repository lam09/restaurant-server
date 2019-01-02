package com.mango.web.service;

import com.mango.web.entity.Food;
import com.mango.web.repo.FoodCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FoodRepositoryImpl implements FoodCustomRepository {


    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Food> selectCustomFoods(Integer page,Integer pageSize) {
        final Pageable pageableRequest = PageRequest.of(page, pageSize);
        Query query = new Query();
        query.with(pageableRequest);
        return mongoTemplate.find(query,Food.class);
    }
}
