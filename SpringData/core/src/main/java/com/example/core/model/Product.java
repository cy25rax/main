package com.example.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

//import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "engine")
    private String engine;

    public Product(String title, Long cost, String engine) {
        this.title = title;
        this.cost = cost;
        this.engine = engine;
    }

    public Product() {
    }
}
