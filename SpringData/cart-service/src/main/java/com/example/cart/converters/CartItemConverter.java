package com.example.cart.converters;

import com.example.api.CartItemDto;
import com.example.cart.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {

    public CartItemDto convertToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto(
                cartItem.getProductId(),
                cartItem.getProductTitle(),
                cartItem.getQuantity(),
                cartItem.getCostPerProduct(),
                cartItem.getCost()
        );
        return cartItemDto;
    }

}
