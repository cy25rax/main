package com.example.core.service;

import com.example.core.interfaces.ProductRepository;
import com.example.core.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
//@ActiveProfiles("test")
class ProductRepositoryServiceTest {
	
	@Autowired
	ProductRepository productRepository;
	
	Product testProduct;
	
	@BeforeAll
	void init() {
		testProduct = new Product();
		testProduct.setCost(new BigDecimal(10));
		testProduct.setTitle("testTitle");
	}
	
	@Test
	void save() {
		productRepository.save(testProduct);
		
		Assertions.assertNotNull(testProduct);
		Assertions.assertNotNull(testProduct.getId());
		Assertions.assertEquals("testTitle", testProduct.getTitle());
		
		Product savedProduct = productRepository.findById(testProduct.getId()).orElse(null);
		Assertions.assertEquals("testTitle", savedProduct.getTitle());
		
	}

}