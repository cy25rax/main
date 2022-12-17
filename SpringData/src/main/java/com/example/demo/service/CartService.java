package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private Cart cart;
    @Autowired
    private ProductRepositoryService productRepositoryService;

    public void addToCart(Long id) {
        Product product = productRepositoryService.getReferenceById(id);
        ProductDTO productDTO = new ProductDTO(product);
        cart.addToCart(productDTO);
    }

    public List<ProductDTO> findAllCartProducts() {
        return cart.findAllCartProducts();
    }

    public void deleteProduct(Long id) {
        cart.deleteProductFromCart(id);
    }
}
