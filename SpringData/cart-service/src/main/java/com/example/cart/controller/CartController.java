package com.example.cart.controller;

import com.example.api.CartDto;
import com.example.api.StringResponse;
import com.example.cart.converters.CartConverter;
import com.example.cart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("cart/v1")
//@CrossOrigin("*")
public class CartController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private CartConverter cartConverter;
	
	@GetMapping("/{uuid}/addQuantity/{id}")
	public void addQuantity(@RequestHeader(name = "username", required = false) String username,
							@PathVariable String uuid,
							@PathVariable Long id,
							@RequestParam int quantity) {
		String targetUuid = getCartUuid(username, uuid);
		cartService.addQuantity(id, quantity, targetUuid);
	}
	
	
	@GetMapping("/generate_uuid")
	public StringResponse generateUuid() {
		return new StringResponse(UUID.randomUUID().toString());
	}
	
	@GetMapping("/{uuid}/add/{id}")
	public void addToCart(@RequestHeader(name = "username", required = false) String username,
						  @PathVariable String uuid,
						  @PathVariable Long id) {
		String targetUuid = getCartUuid(username, uuid);
		cartService.add(targetUuid, id);
	}
	
	@GetMapping("/{uuid}/clear")
	public void clearCart(@RequestHeader(name = "username", required = false) String username,
						  @PathVariable String uuid) {
		String targetUuid = getCartUuid(username, uuid);
		cartService.clear(targetUuid);
	}
	
	@GetMapping("/{uuid}/remove/{id}")
	public void removeFromCart(@RequestHeader(name = "username", required = false) String username,
							   @PathVariable String uuid,
							   @PathVariable Long id) {
		String targetUuid = getCartUuid(username, uuid);
		cartService.remove(targetUuid, id);
	}
	
	@GetMapping("/{uuid}")
	public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) String username,
								  @PathVariable String uuid) {
		String targetUuid = getCartUuid(username, uuid);
		return cartConverter.convertToDto(cartService.getCurrentCart(targetUuid));
	}
	
	private String getCartUuid(String username, String uuid) {
		if (username != null) {
			return username;
		}
		return uuid;
	}
	
}
