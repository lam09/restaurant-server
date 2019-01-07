package com.mango.web.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * Created by a.lam.tuan on 17. 7. 2018.
 */
@Document(collection = "menu")
public class Menu{
    @Id
    String id;
    List<Food> food_ids;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
