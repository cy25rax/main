package com.example.cart.converters;

import com.example.api.CartDto;
import com.example.cart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartConverter {

    @Autowired
    CartItemConverter cartItemConverter;

    public CartDto convertToDto(Cart cart) {
        CartDto cartDto = new CartDto(
                 cart.getCartItemList()
                   .stream()
                   .map(cartItemConverter::convertToDto).toList(),
                cart.getTotalCost(),
                cart.getUserName()
        );
        return cartDto;
    }

}
