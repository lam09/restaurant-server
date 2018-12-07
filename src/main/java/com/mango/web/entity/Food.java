package com.mango.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by a.lam.tuan on 17. 7. 2018.
 */

@Entity
@Table(name = "food")
public class Food{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(name = "weight")
    Integer weight=0; // in gram
    @Column(name = "allergens")
    String allergens="";
    @Column(name = "name")
    String name;
    @Column(name = "description")
    String description;
    @Column(name = "price")
    Double price;
    @Column(name = "groupName")
    String groupName;
    @Column(name = "status")
    String status;

    @Column(name = "parent")
    Food parent;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    Menu menu;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    public Food getParent()
    {
        return parent;
    }
    public void setParent(Food parent)
    {this.parent=parent;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Food(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGroup() {
        return groupName;
    }

    public void setGroup(String groupName) {
        this.groupName = groupName;
    }


}
