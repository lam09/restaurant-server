package com.mango.web.repo;

import com.mango.web.entity.Order;

public interface OrderCustomRepository {
    public Order addNextOrderInday(Order order);
    Integer lastOrderNoToday();
}
