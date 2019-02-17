package com.mango.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;


/**
 * Created by a.lam.tuan on 17. 7. 2018.
 */

@Document(collection = "food")
public class Food implements Serializable {
    @Id
    private String id;
    @Field(value = "serial")
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private Integer serial;
    @Field(value = "title")
    private String title;
    @Field(value = "price")
    private String price;
    @Field(value = "description")
    private String description;
    @Field(value = "type")
    private String type;


    public Food(){}

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

