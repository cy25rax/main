package com.example.cart.services;

import com.example.api.CartItemDto;
import com.example.api.ProductDTO;
import com.example.cart.converters.CartItemConverter;
import com.example.cart.integration.ProductServiceIntegration;
import com.example.cart.model.Cart;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private Cart cart;

    @Autowired
    private CartItemConverter cartItemConverter;
    @Autowired
    private ProductServiceIntegration productServiceIntegration;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public void addToCart(Long id) {
        ProductDTO productDTO = productServiceIntegration.getProductById(id);
        cart.addToCart(productDTO);
    }

    public Cart getCart() {
        return cart;
    }

    public void deleteProduct(Long id) {
        cart.deleteProductFromCart(id);
    }

    public void eraseCart() {
        cart.eraseCart();
    }

    public void addQuantity(Long id, int quantity) {
        cart.addQuantity(id, quantity);
    }

}
