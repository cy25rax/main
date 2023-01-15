package com.example.cart.controller;

import com.example.api.ProductDTO;
import com.example.cart.integration.ProductServiceIntegration;
import com.example.cart.services.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CartControllerTest {
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	CartService cartService;
	
	@MockBean
	ProductServiceIntegration productServiceIntegration;

	
	@Test
	void addQuantity() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(1L);
		productDTO.setTitle("test");
		productDTO.setCost(new BigDecimal(2));
		
		Mockito.when(productServiceIntegration.getProductById(1L)).thenReturn(productDTO);
		
		cartService.addToCart(1L);
		
		webTestClient.get()
				.uri("cart/v1/addQuantity/1?quantity=1")
				.exchange();
		
		Assertions.assertEquals(cartService.getCart().getCartItemList().get(0).getQuantity(), 2);
		Assertions.assertEquals(cartService.getCart().getCartItemList().get(0).getCost(), new BigDecimal(4));
		
	}
	

}