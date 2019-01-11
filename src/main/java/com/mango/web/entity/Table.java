package com.mango.web.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "table")
public class Table {
    @Id
    String id;
    @Field(value = "tableNo")
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    Integer tableNo;
    String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
