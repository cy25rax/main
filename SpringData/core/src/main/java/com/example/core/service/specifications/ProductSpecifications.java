package com.example.core.service.specifications;

import com.example.core.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecifications {

    public static Specification<Product> findByCostLessThanEqual(BigDecimal cost){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
                root.get("cost"), cost));
    }

    public static Specification<Product> findByCostGreaterThanEqual(BigDecimal cost){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
                root.get("cost"), cost));
    }

    public static Specification<Product> titleLike(String title){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get("title"), "%" + title + "%"));
    }

}