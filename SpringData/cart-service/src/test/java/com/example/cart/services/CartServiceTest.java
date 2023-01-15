package com.example.cart.services;

import com.example.api.ProductDTO;
import com.example.cart.integration.ProductServiceIntegration;
import com.example.cart.model.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CartServiceTest {
	
	@Autowired
	CartService cartService;
	
	@MockBean
	ProductServiceIntegration productServiceIntegration;
	
	static ProductDTO productDTO;
	
	@BeforeAll
	static void initTest() {
		productDTO = new ProductDTO();
		productDTO.setId(1L);
		productDTO.setTitle("test");
		productDTO.setCost(new BigDecimal(1));
	}
	
	@Test
	void addToCart() {
		cartService.init();
		Mockito.when(productServiceIntegration.getProductById(1L)).thenReturn(productDTO);
		cartService.addToCart(1L);
		
		Cart savedIntoCart = cartService.getCart();
		
		Assertions.assertEquals(savedIntoCart.getCartItemList().get(0).getProductTitle(), productDTO.getTitle());
		Assertions.assertEquals(savedIntoCart.getCartItemList().get(0).getCost(), productDTO.getCost());
		Assertions.assertEquals(savedIntoCart.getTotalCost(), new BigDecimal(1));
		
	}
	
	@Test
	void deleteProduct() {
		cartService.init();
		Mockito.when(productServiceIntegration.getProductById(1L)).thenReturn(productDTO);
		cartService.addToCart(1L);
		
		cartService.deleteProduct(1L);
		Cart savedIntoCart = cartService.getCart();
		
		Assertions.assertEquals(savedIntoCart.getCartItemList().size(), 0);
		
	}
	
	@Test
	void addQuantity() {
		cartService.init();
		Mockito.when(productServiceIntegration.getProductById(1L)).thenReturn(productDTO);
		cartService.addToCart(1L);
		
		cartService.addQuantity(1L, 1);
		Cart savedIntoCart = cartService.getCart();
		
		Assertions.assertEquals(savedIntoCart.getCartItemList().get(0).getQuantity(), 2);
		
	}
}