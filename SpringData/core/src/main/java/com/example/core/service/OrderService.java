package com.example.core.service;

import com.example.api.CartDto;
import com.example.api.CartItemDto;
import com.example.core.integration.CartServiceIntegration;
import com.example.core.interfaces.OrderItemRepository;
import com.example.core.interfaces.OrderRepository;
import com.example.core.model.Order;
import com.example.core.model.OrderItem;
import com.example.core.security2.security.DatabaseUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartServiceIntegration cartService;
    private final ProductRepositoryService productRepositoryService;
    private final DatabaseUserDetailsService userDetailsService;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private CartDto cart;
    private List<OrderItem> orderItemList;

    @Transactional
    public void createOrder(String  userName) {

        orderItemList = new ArrayList<>();
        cart = cartService.getCart();

        Order order = new Order(null,
                userDetailsService.findByUserName(userName),
                orderItemList,
                "KMarksa 234",
                "1234567890",
                cart.getTotalCost());

        orderRepository.save(order);

        for (CartItemDto cartItem: cart.getCartItemList()) {
            OrderItem orderItem = new OrderItem(null,
                    productRepositoryService.getReferenceById(cartItem.getProductId()),
                    order,
                    cartItem.getQuantity(),
                    cartItem.getCostPerProduct(),
                    cartItem.getCost());
            orderItemRepository.save(orderItem);
            orderItemList.add(orderItem);
        }

        cartService.clearCart();
    }

}
