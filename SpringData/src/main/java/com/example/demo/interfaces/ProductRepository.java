package com.example.demo.interfaces;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

//    @Query(value = "select p from products p where p.cost = :cost")
//    List<Product> findBelowCost(int cost);
//
//    @Query(value = "select * from products")
//    List<Product> findBCostLessThen (Long cost);
}
