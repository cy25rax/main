package com.example.demo.sexurity.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.ProductDTO;
import com.example.demo.service.ProductRepositoryService;
import com.example.demo.sexurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/v2")
public class Securcontroller {

    @Autowired
    private ProductRepositoryService productRepositoryService;
    @Autowired
    private UserService userService;

    @GetMapping("/security")
    public String showProductList(@RequestParam(required = false) Integer minCost,
                               @RequestParam(required = false) Integer maxCost,
                               @RequestParam(required = false) String title,
                               Model model) {
        model.addAttribute("allProducts",
                productRepositoryService.findAll(minCost, maxCost, title));

        return "security";
    }

    @PostMapping("/{id}")
    public String deleteById(@PathVariable Long id, Model model) {

        productRepositoryService.deleteById(id);
        model.addAttribute("allProducts",
                productRepositoryService.findAll(null, null, null));

        return "redirect:/v2/security";
    }

    @GetMapping("/users")
    public String showUsersList (Model model) {
        model.addAttribute("users",
                userService.findAll());

        return "users";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String title,
                             @RequestParam Long cost,
                             @RequestParam String engine, Model model) {
        Product product = new Product();
        product.setId(null);
        product.setTitle(title);
        product.setEngine(engine);
        product.setCost(cost);
        productRepositoryService.save(product);
        model.addAttribute("allProducts",
                productRepositoryService.findAll(null, null, null));
        return "redirect:/v2/security";
    }

}
