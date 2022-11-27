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
        productRepository.save(new Product("corolla", 10L));
        productRepository.save(new Product("rx", 12L));
        productRepository.save(new Product("crown", 20L));
        productRepository.save(new Product("celsior", 25L));
        productRepository.save(new Product("century", 30L));
        productRepository.save(new Product("markII", 18L));
        productRepository.save(new Product("chaser", 19L));
        productRepository.save(new Product("alphard", 28L));
        productRepository.save(new Product("tundra", 25L));
        productRepository.save(new Product("land cruiser", 21L));
        productRepository.save(new Product("majesta", 27L));
    }

}
