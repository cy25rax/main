package com.example.demo.model;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String title;
    private Long cost;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
    }

    public ProductDTO() {
    }
}
