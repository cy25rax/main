package com.example.service;

import com.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product("молоко", 50),
            new Product("колбоса", 150)
    ));

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
