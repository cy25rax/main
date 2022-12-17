package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
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

    @GetMapping("/add/{id}")
    public List<ProductDTO> cartAdd(@PathVariable Long id) {
        cartService.addToCart(id);
        return cartService.findAllCartProducts();
    }

    @GetMapping("/deleteProduct/{id}")
    public List<ProductDTO> deleteProduct(@PathVariable Long id) {
        cartService.deleteProduct(id);
        return cartService.findAllCartProducts();
    }
}
