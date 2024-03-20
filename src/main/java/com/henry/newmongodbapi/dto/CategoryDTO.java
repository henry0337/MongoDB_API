package com.henry.newmongodbapi.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private String name;
    private String description;
    private Short status;
}
