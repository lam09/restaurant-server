package com.mango.web.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by a.lam.tuan on 17. 7. 2018.
 */
@Document(collection = "menu")
public class Menu{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "date")
    Date date;
    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL)
    List<Food> foodList;

    public Menu(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
