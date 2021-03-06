package com.mango.web.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Document(collection = "restaurant")
public class Restaurant implements Serializable {

    @Id
    String id;

    @Field("number")
    Integer number;

    @Field("name")
    String name;

    String address;

    String telefon;
    @DBRef
    @JsonIgnore
    Account admin;

    @DBRef
    @JsonIgnore
    List<Account> accounts;

    @DBRef
    @JsonIgnore
    List<Food> foods;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Account getAdmin() {
        return admin;
    }

    public void setAdmin(Account adminAccount) {
        this.admin = adminAccount;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
