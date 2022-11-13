package com.example.repository;

import com.example.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.add(new Product("BMW", 10));
        products.add(new Product("Lada", 5));
        products.add(new Product("Mercedes", 20));
        products.add(new Product("Toyota", 13));
        products.add(new Product("KAI", 17));
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById (int id){
        Product product = products.stream()
                .filter(it -> Objects.equals(id, it.getId()))
                .findFirst()
                .orElse(null);
        return product;
    }

    public List<Product> deleteById(int id) {
        for (Product p:products){
            if (p.getId() == id) {
                products.remove(p);
                return products;
            }
        }
        return products;
    }

}
