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
        productRepository.save(new Product("corolla", 10l));
        productRepository.save(new Product("rx", 12l));
        productRepository.save(new Product("crown", 20l));
        productRepository.save(new Product("celsior", 25l));
        productRepository.save(new Product("century", 30l));
        productRepository.save(new Product("markII", 18l));
    }

}
