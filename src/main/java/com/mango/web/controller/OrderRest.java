package com.mango.web.controller;

import com.mango.web.entity.Food;
import com.mango.web.entity.Order;
import com.mango.web.entity.OrderItem;
import com.mango.web.repo.FoodRepository;
import com.mango.web.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderRest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    FoodRepository foodRepository;
    @ResponseBody
    @RequestMapping(value = "/order/newOrder",method = RequestMethod.POST,
    consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public Order addNewOrder(@RequestBody Integer[] serialList){
        Order order = new Order();
        ArrayList<OrderItem>foods=new ArrayList<>();
        for(Integer f:serialList){
            Food food=foodRepository.findFoodBySerial(f);
            OrderItem orderItem = new OrderItem();
            orderItem.setFoodSerial(food.getSerial());
            orderItem.setPrice(food.getPrice());
            orderItem.setTitle(food.getTitle());
            foods.add(orderItem);
        }
        order.setOrder_items(foods);
        Integer orderNo=orderRepository.lastOrderNoToday();
        order.setOrderNo(orderNo);
        order.setDate(new Date());
        return orderRepository.save(order);
    }

}
