package com.example.cart.controller;

import com.example.api.CartDto;
import com.example.cart.converters.CartConverter;
import com.example.cart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart/v1")
//@CrossOrigin("*")
public class CartController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private CartConverter cartConverter;
	
	@GetMapping
	public CartDto showCart(@RequestHeader(required = false) String username) {
		return cartConverter.convertToDto(cartService.getCart(username));
	}
	
	@GetMapping("/add/{id}")
	public void cartAdd(@PathVariable Long id,
						@RequestHeader(required = false) String username) {
		cartService.addToCart(id, username);
	}
	
	@GetMapping("/deleteCartItem/{id}")
	public void deleteCartItem(@PathVariable Long id,
							   @RequestHeader(required = false) String username) {
		cartService.deleteProduct(id, username);
	}
	
	@GetMapping("/eraseCart")
	public void eraseCart() {
		cartService.eraseCart();
	}
	
	@GetMapping("/addQuantity/{id}")
	public void addQuantity(@PathVariable Long id,
							@RequestParam int quantity,
							@RequestHeader(required = false) String username) {
		cartService.addQuantity(id, quantity, username);
	}
	
}
