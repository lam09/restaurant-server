package com.mango.web.controller;

import com.mango.web.entity.Food;
import com.mango.web.entity.Order;
import com.mango.web.entity.OrderItem;
import com.mango.web.forms.OrderForm;
import com.mango.web.repo.FoodRepository;
import com.mango.web.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderRest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    FoodRepository foodRepository;
    @ResponseBody
    @RequestMapping(value = "/order/newOrder",method = RequestMethod.POST,
    consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public Order addNewOrder(@RequestBody OrderForm orderForm){
        Order order = new Order();
        order.setOrder_items(getOrderItem(orderForm.getFoodSerialList()));
        order.setTableNo(orderForm.getTableNo());
        Integer orderNo=orderRepository.lastOrderNoToday();
        order.setOrderNo(orderNo);
        order.setDate(new Date());

        return orderRepository.save(order);
    }
    ArrayList<OrderItem> getOrderItem(Integer[] serialList){
        ArrayList<OrderItem>foods=new ArrayList<>();
        for(Integer f:serialList){
            Food food=foodRepository.findFoodBySerial(f);
            OrderItem orderItem = new OrderItem();
            orderItem.setFoodSerial(food.getSerial());
            orderItem.setPrice(food.getPrice());
            orderItem.setTitle(food.getTitle());
            foods.add(orderItem);
        }
        return foods;
    }

    @ResponseBody
    @RequestMapping(value = "/order/update",method = RequestMethod.POST,
    consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public Order updateOrder(@RequestBody OrderForm orderForm)
    {
        Order order=orderRepository.findOrderByOrderNo(orderForm.getOrderNo());
        order.setOrder_items(getOrderItem(orderForm.getFoodSerialList()));
        order.setTableNo(orderForm.getTableNo());
        order.setOrderState(orderForm.getState());
        return orderRepository.save(order);
    }

    @ResponseBody
    @RequestMapping(value = "/order/get",method = RequestMethod.GET,
    consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Order> getOrder(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
                                @RequestParam("state") Optional<String>state,@RequestParam("date") Optional<String>date)
    {
        return orderRepository.getOrders(page,pageSize,state,date);
    }

}
