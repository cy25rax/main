package com.example.core.controller;

import com.example.api.ProductDTO;
import com.example.core.interfaces.ProductRepository;
import com.example.core.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@ActiveProfiles("test")
class ProductRestControllerTest {
	
	@MockBean
	ProductRepository repository;
	
	@Autowired
	WebTestClient webTestClient;
	
	static Product product;
	
	@BeforeAll
	static void init() {
		product = new Product();
		product.setId(1L);
		product.setTitle("test");
	}
	
	@Test
	void findById() {
		Mockito.when(repository.getReferenceById(product.getId())).thenReturn(product);
		
		ProductDTO productByHttp = webTestClient.get()
										  .uri("products/v1/" + product.getId())
										  .exchange()
										  .expectBody(ProductDTO.class)
										  .returnResult()
										  .getResponseBody();
		
		Assertions.assertEquals(product.getId(), productByHttp.getId());
		Assertions.assertEquals(product.getTitle(), productByHttp.getTitle());
	}
	
	@Test
	void addProduct() {
		
		ProductDTO productByHttp = webTestClient.post()
										   .uri("products/v1")
										   .bodyValue(product)
										   .exchange()
										   .expectBody(ProductDTO.class)
										   .returnResult()
										   .getResponseBody();
		
		Assertions.assertEquals(product.getTitle(), productByHttp.getTitle());
	}

}