package com.example.demo.service;

import com.example.demo.interfaces.ProductRepository;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductRepositoryService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product getReferenceById(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public List<Product> findByCostLessThanEqual(Long cost) {
        return productRepository.findByCostLessThanEqual(cost);
    }

    @Transactional
    public List<Product> findByCostBetween(Long minCost, Long maxCost) {
        return productRepository.findByCostBetween(minCost, maxCost);
    }
}
