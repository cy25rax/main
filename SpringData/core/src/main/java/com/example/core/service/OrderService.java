package com.example.core.service;

import com.example.api.CartDto;
import com.example.api.CartItemDto;
import com.example.core.integration.CartServiceIntegration;
import com.example.core.interfaces.OrderItemRepository;
import com.example.core.interfaces.OrderRepository;
import com.example.core.model.Order;
import com.example.core.model.OrderItem;
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
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(String  userName) {

        List<OrderItem> orderItemList = new ArrayList<>();

        System.out.println("cart find ");

        CartDto cart = cartService.getCart(userName);

        System.out.println("cart " + cart);

        Order order = new Order(null,
                userName,
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

        cartService.clearCart(userName);
    }

}
