package com.example.core.converters;

import com.example.api.OrderItemDto;
import com.example.core.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem) {
        return OrderItemDto.builder()
                       .id(orderItem.getId())
                       .price(orderItem.getPrice())
                       .quantity(orderItem.getQuantity())
                       .pricePerProduct(orderItem.getPricePerProduct())
                       .productTitle(orderItem.getProduct().getTitle())
                       .build();
    }
}
