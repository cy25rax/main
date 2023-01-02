package com.example.core.interfaces;

import com.example.core.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

//    @Query(value = "select p from products p where p.cost = :cost")
//    List<Product> findBelowCost(int cost);
//
//    @Query(value = "select * from products")
//    List<Product> findBCostLessThen (Long cost);
}
