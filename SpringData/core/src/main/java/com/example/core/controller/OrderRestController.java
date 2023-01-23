package com.example.core.controller;

import com.example.api.OrderDto;
import com.example.core.converters.OrderConverter;
import com.example.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders/v1")
//@CrossOrigin("*")
public class OrderRestController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderConverter orderConverter;
    
    
    @GetMapping("/createOrder")
    public void createOrder(@RequestHeader String username) {
        orderService.createOrder(username);
    }
    
    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader String username) {
        return orderService.findByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }

}