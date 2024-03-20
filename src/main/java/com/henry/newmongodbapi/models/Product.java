package com.henry.newmongodbapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @MongoId(FieldType.OBJECT_ID)
    private String _id;

    private String name;

    private String image;

    private String description;

    private Short status;

    @DBRef
    private Category category;
}
