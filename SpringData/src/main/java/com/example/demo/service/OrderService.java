package com.example.demo.service;

import com.example.demo.interfaces.OrderItemRepository;
import com.example.demo.interfaces.OrderRepository;
import com.example.demo.model.CartItem;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.security2.security.DatabaseUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final ProductRepositoryService productRepositoryService;
    private final DatabaseUserDetailsService userDetailsService;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private List<CartItem> cartItemList;
    private List<OrderItem> orderItemList;

    public void createOrder(String  userName) {

        orderItemList = new ArrayList<>();
        cartItemList = new ArrayList<>();
        cartItemList = cartService.findAllCartProducts().findAllCartItems();

        Order order = new Order(null,
                userDetailsService.findByUserName(userName),
                orderItemList,
                "KMarksa 234",
                "1234567890",
                cartService.totalCost());

        orderRepository.save(order);

        for (CartItem cartItem: cartItemList) {
            OrderItem orderItem = new OrderItem(null,
                    productRepositoryService.getReferenceById(cartItem.getProductId()),
                    order,
                    cartItem.getQuantity(),
                    cartItem.getCostPerProduct(),
                    cartItem.getCost());
            orderItemRepository.save(orderItem);
            orderItemList.add(orderItem);
        }
    }

}
