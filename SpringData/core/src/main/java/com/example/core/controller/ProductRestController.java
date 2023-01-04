package com.example.core.controller;

import com.example.api.ProductDTO;
import com.example.core.model.Product;
import com.example.core.service.OrderService;
import com.example.core.service.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("products/v1")
//@CrossOrigin("*")
public class ProductRestController {

    @Autowired
    private ProductRepositoryService productRepositoryService;

    @GetMapping
    public List<ProductDTO> findAll(@RequestParam(required = false) Integer minCost,
                                    @RequestParam(required = false) Integer maxCost,
                                    @RequestParam(required = false) String title){
        System.out.println(" cost " + minCost);
        System.out.println(" cost " + maxCost);
        System.out.println(" title " + title);

        return  productRepositoryService.findAll(minCost, maxCost, title)
                .stream().map(item -> new ProductDTO(item.getId(), item.getTitle(), item.getCost())).toList();
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        Product product = productRepositoryService.getReferenceById(id);
        return new ProductDTO(product.getId(), product.getTitle(), product.getCost());
    }

    @PutMapping
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        Product product = productRepositoryService.getReferenceById(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setCost(productDTO.getCost());
        productRepositoryService.save(product);
        return new ProductDTO(product.getId(), product.getTitle(), product.getCost());
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO){
        Product product = new Product();
        product.setId(null);
        product.setCost(productDTO.getCost());
        product.setTitle(productDTO.getTitle());
        productRepositoryService.save(product);
        return new ProductDTO(product.getId(), product.getTitle(), product.getCost());
    }

    @DeleteMapping("/{id}")
    public Stream<ProductDTO> deleteById (@RequestParam(required = false) Integer minCost,
                                          @RequestParam(required = false) Integer maxCost,
                                          @RequestParam(required = false) String title,
                                          @PathVariable Long id){
        productRepositoryService.deleteById(id);
        return productRepositoryService.findAll(minCost, maxCost, title)
                .stream().map(item -> new ProductDTO(item.getId(), item.getTitle(), item.getCost()));
    }

}