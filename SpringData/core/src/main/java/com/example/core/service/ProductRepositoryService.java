package com.example.core.service;

import com.example.core.interfaces.ProductRepository;
import com.example.core.model.Product;
import com.example.core.service.specifications.ProductSpecifications;
import com.example.core.soap.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductRepositoryService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<Product> findAll(BigDecimal minPrice, BigDecimal maxPrice, String title) {
        Specification<Product> specification = Specification.where(null);

        if (minPrice != null) {
            specification = specification.and(ProductSpecifications.findByCostGreaterThanEqual(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecifications.findByCostLessThanEqual(maxPrice));
        }
        if (title != null && title.length()!=0) {
            specification = specification.and(ProductSpecifications.titleLike(title));
        }

        return productRepository.findAll(specification);
    }

    @Transactional
    public Product getReferenceById(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    //SOAP
    public static final Function<Product, ProductEntity> functionToSoap = se -> {
        ProductEntity product = new ProductEntity();
        product.setId(se.getId());
        product.setTitle(se.getTitle());
        product.setCost(se.getCost());
        return product;
    };
    
    //SOAP
    public List<ProductEntity> getAllProduct() {
        return productRepository.findAll().stream().map(functionToSoap).collect(Collectors.toList());
    }
    
    //SOAP
    public ProductEntity getProductById(Long id) {
        ProductEntity productEntity = new ProductEntity();
        Product product = productRepository.getReferenceById(id);
        productEntity.setId(product.getId());
        productEntity.setTitle(productEntity.getTitle());
        productEntity.setCost(product.getCost());
        return productEntity;
    }

}
