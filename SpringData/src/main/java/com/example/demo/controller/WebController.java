package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.ProductDTO;
import com.example.demo.service.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("v1/products")
public class WebController {

    @Autowired
    private ProductRepositoryService productRepositoryService;

    @GetMapping
    public List<ProductDTO> findAll(@RequestParam(required = false) Integer minCost,
                                    @RequestParam(required = false) Integer maxCost,
                                    @RequestParam(required = false) String title){
        return  productRepositoryService.findAll(minCost, maxCost, title)
                .stream().map(ProductDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        Product product = productRepositoryService.getReferenceById(id);
        return new ProductDTO(product);
    }

    @PutMapping
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        Product product = productRepositoryService.getReferenceById(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setCost(productDTO.getCost());
        productRepositoryService.save(product);
        return new ProductDTO(product);
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO){
        Product product = new Product();
        product.setId(null);
        product.setCost(productDTO.getCost());
        product.setTitle(productDTO.getTitle());
        productRepositoryService.save(product);
        return new ProductDTO(product);
    }

    @DeleteMapping("/{id}")
    public Stream<ProductDTO> deleteById (@RequestParam(required = false) Integer minCost,
                                          @RequestParam(required = false) Integer maxCost,
                                          @RequestParam(required = false) String title,
                                          @PathVariable Long id){
        productRepositoryService.deleteById(id);
        return productRepositoryService.findAll(minCost, maxCost, title).stream().map(ProductDTO::new);
    }

}