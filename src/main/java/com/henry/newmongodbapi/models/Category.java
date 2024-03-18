package com.henry.newmongodbapi.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "categories")
public class Category {
    @Id
    private ObjectId _id;

    private Integer categoryId;

    @Field("categoryName")
    private String name;

    private String description;

    private Short status;
}