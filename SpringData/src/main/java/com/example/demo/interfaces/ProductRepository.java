package com.example.demo.interfaces;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query(value = "select p from products p where p.cost = :cost")
//    List<Product> findBelowCost(int cost);
//
//    @Query(value = "select * from products")
//    List<Product> findBCostLessThen (Long cost);

    List<Product> findByCostLessThanEqual (Long cost);

    List<Product> findByCostBetween (Long minCost, Long maxCost);

    List<Product> findByCostGreaterThanEqual(Long minCost);
}
