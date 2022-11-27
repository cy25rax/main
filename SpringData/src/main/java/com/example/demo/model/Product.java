package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    public Product() {
    }

    @Column(name = "cost")
    private Long cost;

    public Product(String title, Long cost) {
        this.title = title;
        this.cost = cost;
    }
}
