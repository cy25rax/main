package com.example.demo.service;

import com.example.demo.interfaces.ProductRepository;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FillDatabase {

    @Autowired
    ProductRepository productRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void fillBase(){
        productRepository.save(new Product("corolla", 10L, "3s"));
        productRepository.save(new Product("rx", 12L, "3s"));
        productRepository.save(new Product("crown", 20L, "1jz"));
        productRepository.save(new Product("celsior", 25L, "2uz"));
        productRepository.save(new Product("century", 30L, "1gz"));
        productRepository.save(new Product("markII", 18L, "1g"));
        productRepository.save(new Product("chaser", 19L, "2jz"));
        productRepository.save(new Product("alphard", 28L, "2gr"));
        productRepository.save(new Product("tundra", 25L, "3ur"));
        productRepository.save(new Product("land cruiser", 21L, "1ur"));
        productRepository.save(new Product("majesta", 27L, "1uz"));
    }

}
