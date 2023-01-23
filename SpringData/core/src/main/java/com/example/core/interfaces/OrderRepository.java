package com.example.core.interfaces;

import com.example.core.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByUserName(String username);
	
}
