package com.mango.web.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Document(collection = "order")
public class Order {

    @Id
    String id;
    Integer orderNo;
    Time time;
    Date date;
//    List<Food> food_ids;
    List<OrderItem> order_items;
    //@Indexed(unique = true,direction = IndexDirection.DESCENDING,dropDups = true)
    Integer tableNo;

    public Integer getTableNo() {
        return tableNo;
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }

    public List<OrderItem> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(List<OrderItem> order_items) {
        this.order_items = order_items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}
