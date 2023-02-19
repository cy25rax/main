package com.example.cart.converters;

import com.example.api.CartItemDto;
import com.example.cart.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {

    public CartItemDto convertToDto(CartItem cartItem) {
        return CartItemDto.builder()
                       .productId(cartItem.getProductId())
                       .productTitle(cartItem.getProductTitle())
                       .quantity(cartItem.getQuantity())
                       .costPerProduct(cartItem.getCostPerProduct())
                       .cost(cartItem.getCost())
                       .build();
    }

}
