package com.example.core.controller;

import com.example.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("v1/orders")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/createOrder")
    public void findById(Principal principal) {
        orderService.createOrder(principal.getName());
    }

}