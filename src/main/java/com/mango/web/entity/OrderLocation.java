package com.mango.web.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "OrderLocation")
public class OrderLocation {
    @Id
    String id;
    @Field(value = "orderLocationNo")
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    Integer orderLocationNo;
    String description;
    Integer capacita;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOrderLocationNo() {
        return orderLocationNo;
    }

    public void setOrderLocationNo(Integer orderLocationNo) {
        this.orderLocationNo = orderLocationNo;
    }

    public Integer getCapacita() {
        return capacita;
    }

    public void setCapacita(Integer capacita) {
        this.capacita = capacita;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
