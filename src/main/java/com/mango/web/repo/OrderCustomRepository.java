package com.mango.web.repo;

import com.mango.web.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderCustomRepository {
    Integer lastOrderNoToday();
    Order findOrderByOrderNo(Integer orderNo); //find order with number today
    List<Order> getOrders(Integer page, Integer pageSize, Optional<String> state, Optional<String> date);
}
