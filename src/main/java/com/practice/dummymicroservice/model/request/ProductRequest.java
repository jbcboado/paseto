package com.practice.dummymicroservice.model.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Long price;
}
