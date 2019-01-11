package com.mango.web.service;

import com.mango.web.entity.Food;
import com.mango.web.entity.Order;
import com.mango.web.repo.OrderCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderRepositoryImpl implements OrderCustomRepository {
    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public Integer lastOrderNoToday(){
        Query query = new Query();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        System.out.println(dateFormat.format(today));
        //query.addCriteria(Criteria.where("date").gte("2019-01-01"));
        query.with(new Sort(Sort.Direction.DESC, "orderNo"));
        query.limit(1);
        List<Order> orders = mongoTemplate.find(query,Order.class);
        int lastOrderNo=1;
        if(orders!=null&&orders.size()>0){
            System.out.println("Today we received "+orders.size());
            lastOrderNo = orders.get(0).getOrderNo();
            lastOrderNo++;
        }
        return lastOrderNo;
    }

    @Override
    public Order findOrderByOrderNo(Integer orderNo) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC,"date"));
        query.addCriteria(Criteria.where("orderNo").is(orderNo));
        return null;
    }

}
