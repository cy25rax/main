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
    public CartDto showCart(){
        return cartConverter.convertToDto(cartService.getCart());
    }

    @GetMapping("/add/{id}")
    public void cartAdd(@PathVariable Long id) {
        cartService.addToCart(id);
    }

    @GetMapping("/deleteCartItem/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartService.deleteProduct(id);
    }

    @DeleteMapping("/eraseCart")
    public void eraseCart() {
        cartService.eraseCart();
    }

    @GetMapping("/addQuantity/{id}")
    public void addQuantity(@PathVariable Long id,
                            @RequestParam int quantity) {
        cartService.addQuantity(id, quantity);
    }

}
