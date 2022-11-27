package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class WebController {

    @Autowired
    ProductRepositoryService productRepositoryService;

    @GetMapping
    public List<Product> findAll(){
        return  productRepositoryService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productRepositoryService.getReferenceById(id);
    }

    @GetMapping ("/add")
    public List<Product> addProduct(@RequestParam String title, @RequestParam Long cost){
        Product product = new Product();
        product.setTitle(title);
        product.setCost(cost);
        productRepositoryService.save(product);
        return productRepositoryService.findAll();
    }

    @GetMapping("delete/{id}")
    public List<Product> deleteById (@PathVariable Long id){
        productRepositoryService.deleteById(id);
        return productRepositoryService.findAll();
    }

//    http://localhost:8050/products/below/20
    @GetMapping("below/{cost}")
    public List<Product> belowCost (@PathVariable Long cost){
        return productRepositoryService.findByCostLessThanEqual(cost);
    }

//    http://localhost:8050/products/between?minCost=17&maxCost=26
    @GetMapping("between")
    public List<Product> betweenCost (@RequestParam Long minCost, @RequestParam Long maxCost){
        return productRepositoryService.findByCostBetween(minCost, maxCost);
    }
}