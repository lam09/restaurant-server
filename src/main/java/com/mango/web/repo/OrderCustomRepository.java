package com.mango.web.repo;

import com.mango.web.entity.Order;

public interface OrderCustomRepository {
    Integer lastOrderNoToday();
    Order findOrderByOrderNo(Integer orderNo); //find order with number today
}
