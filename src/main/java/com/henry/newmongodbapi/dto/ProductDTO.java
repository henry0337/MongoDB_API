package com.henry.newmongodbapi.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String image;
    private String description;
    private Short status;
    private String categoryId;
}
