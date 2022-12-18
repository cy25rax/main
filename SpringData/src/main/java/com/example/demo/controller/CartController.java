package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.ProductDTO;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/cart")
public class CartController {

    @Autowired
    private ProductRepositoryService productRepositoryService;
    @Autowired
    private CartService cartService;

    @GetMapping
    public Cart showCart(){
        return cartService.findAllCartProducts();
    }

    @GetMapping("/add/{id}")
    public void cartAdd(@PathVariable Long id) {
        cartService.addToCart(id);
    }

    @GetMapping("/deleteCartItem/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartService.deleteProduct(id);
    }

    @GetMapping("/eraseCart")
    public void eraseCart() {
        cartService.eraseCart();
    }

    @GetMapping("/addQuantity/{id}")
    public void addQuantity(@PathVariable Long id,
                            @RequestParam int quantity) {
        cartService.addQuantity(id, quantity);
    }
}
