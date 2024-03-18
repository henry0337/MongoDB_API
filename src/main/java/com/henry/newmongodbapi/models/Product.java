package com.henry.newmongodbapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "products")
public class Product {
    @MongoId(FieldType.OBJECT_ID)
    private ObjectId _id;

    private String name;

    private Integer categoryId;

    private String image;

    private String description;

    private Short status;

    @JsonIgnore
    @DocumentReference(collection = "categories")
    private Category category;
}
