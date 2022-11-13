package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void addProduct(Product p) {
        productRepository.addProduct(p);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById (int id){
        return productRepository.findById(id);
    }

    public List<Product> deleteById(int id) {
        return productRepository.deleteById(id);
    }

}
