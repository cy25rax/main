package com.example.core.controller;

import com.example.api.ProductDTO;
import com.example.core.model.Product;
import com.example.core.service.OrderService;
import com.example.core.service.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("products/v1")
//@CrossOrigin("*")
public class ProductRestController {

    @Autowired
    private ProductRepositoryService productRepositoryService;

    @GetMapping
    public List<ProductDTO> findAll(@RequestParam(required = false) BigDecimal minCost,
                                    @RequestParam(required = false) BigDecimal maxCost,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(defaultValue = "1") Integer page){
        if (page < 1) {
            page = 1;
        }
//        return productRepositoryService.findAll(minCost, maxCost, title, page - 1)
//                       .map(productRepositoryService::entityToDto).getContent();
        
        return  productRepositoryService.findAll(minCost, maxCost, title, page -1)
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
    public Stream<ProductDTO> deleteById (@RequestParam(required = false) BigDecimal minCost,
                                          @RequestParam(required = false) BigDecimal maxCost,
                                          @RequestParam(required = false) String title,
                                          @PathVariable Long id,
                                          @RequestParam(defaultValue = "1", name = "p") Integer page){
        if (page < 1) {
            page = 1;
        }
        productRepositoryService.deleteById(id);
        return productRepositoryService.findAll(minCost, maxCost, title, page)
                .stream().map(item -> new ProductDTO(item.getId(), item.getTitle(), item.getCost()));
    }

}