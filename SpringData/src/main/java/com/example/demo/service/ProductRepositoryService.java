package com.example.demo.service;

import com.example.demo.interfaces.ProductRepository;
import com.example.demo.model.Product;
import com.example.demo.service.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductRepositoryService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<Product> findAll(Integer minPrice, Integer maxPrice, String title) {
        Specification<Product> specification = Specification.where(null);

        if (minPrice != null) {
            specification = specification.and(ProductSpecifications.findByCostGreaterThanEqual(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecifications.findByCostLessThanEqual(maxPrice));
        }
        if (title != null) {
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

    @Transactional
    public List<Product> findByCostLessThanEqual(Long cost) {
        return productRepository.findByCostLessThanEqual(cost);
    }

    @Transactional
    public List<Product> findByCostBetween(Long minCost, Long maxCost) {
        if (minCost==null && maxCost!=null) return findByCostLessThanEqual(maxCost);
        if (minCost!=null && maxCost==null) return findByCostGreaterThanEqual(minCost);
        return productRepository.findByCostBetween(minCost, maxCost);
    }

    private List<Product> findByCostGreaterThanEqual(Long minCost) {
        return productRepository.findByCostGreaterThanEqual(minCost);
    }
}
