package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.ProductDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

//    @Autowired
    private Cart cart;
    @Autowired
    private ProductRepositoryService productRepositoryService;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public void addToCart(Long id) {
        Product product = productRepositoryService.getReferenceById(id);
        ProductDTO productDTO = new ProductDTO(product);
        cart.addToCart(productDTO);
    }

    public Cart findAllCartProducts() {
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
