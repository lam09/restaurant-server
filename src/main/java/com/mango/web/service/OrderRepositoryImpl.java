package com.mango.web.service;

import com.mango.web.entity.Food;
import com.mango.web.entity.Order;
import com.mango.web.repo.OrderCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderRepositoryImpl implements OrderCustomRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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

    @Override
    public List<Order> getOrders(Integer page, Integer pageSize, Optional<String> state, Optional<String> date) {
        Query query = new Query();
        final Pageable pageable = PageRequest.of(page,pageSize);
        query.with(pageable );
        query.with(new Sort(Sort.Direction.DESC,"orderNo"));

        if(state.isPresent()) query.addCriteria(Criteria.where("orderState").is(state.get()));
        if(date.isPresent()){
            // for date format yyyy-MM-dd
            LocalDateTime from = LocalDateTime.parse(date.get(),formatter);
            LocalDateTime to = LocalDateTime.parse(date.get(),formatter);
            query.addCriteria(Criteria.where("date").gte(from).lte(to));
        }
        return mongoTemplate.find(query,Order.class);
    }
}
