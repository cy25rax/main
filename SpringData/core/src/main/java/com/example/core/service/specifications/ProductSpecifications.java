package com.example.core.service.specifications;

import com.example.core.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> findByCostLessThanEqual(Integer cost){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
                root.get("cost"), cost));
    }

    public static Specification<Product> findByCostGreaterThanEqual(Integer cost){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
                root.get("cost"), cost));
    }

    public static Specification<Product> titleLike(String title){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get("title"), title));
    }

}