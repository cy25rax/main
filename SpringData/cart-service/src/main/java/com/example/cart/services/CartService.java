package com.example.cart.services;

import com.example.api.ProductDTO;
import com.example.cart.converters.CartItemConverter;
import com.example.cart.integration.ProductServiceIntegration;
import com.example.cart.model.Cart;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public void checkUserName (String userName) {
        if (userName == null && cart.getUserName() == null) {
            cart.setUserName("общая корзина");
        }
    
        if (userName != null && cart.getUserName().equals("общая корзина")) {
            cart.eraseCart();
            cart.setUserName(userName);
        }
    
        if (userName == null && !cart.getUserName().equals("общая корзина")) {
            cart.eraseCart();
            cart.setUserName("общая корзина");
        }
    }

    public void addToCart(Long id, String userName) {
        checkUserName(userName);
        ProductDTO productDTO = productServiceIntegration.getProductById(id);
        cart.addToCart(productDTO);
    }

    public Cart getCart(String userName) {
        checkUserName(userName);
        return cart;
    }

    public void deleteProduct(Long id, String userName) {
        checkUserName(userName);
        cart.deleteProductFromCart(id);
    }

    public void eraseCart() {
        cart.eraseCart();
    }

    public void addQuantity(Long id, int quantity, String userName) {
        checkUserName(userName);
        cart.addQuantity(id, quantity);
    }

}
