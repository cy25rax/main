package com.example.demo.controller;

import com.example.demo.service.CartService;
import com.example.demo.service.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("index")
public class ThymeleafController {

    @Autowired
    private ProductRepositoryService productRepositoryService;
    @Autowired
    private CartService cartService;

    @GetMapping
    public String showProductList(@RequestParam(required = false) Integer minCost,
                               @RequestParam(required = false) Integer maxCost,
                               @RequestParam(required = false) String title,
                               Model model) {
        model.addAttribute("allProducts",
                productRepositoryService.findAll(minCost, maxCost, title));
        model.addAttribute("cart",
                cartService.findAllCartProducts());

        return "index";
    }

    @PostMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        System.out.println(id);
        productRepositoryService.deleteById(id);
        return "redirect:/index";
    }

    @PostMapping("cartAdd/{id}")
    public String cartAdd(@PathVariable Long id) {
        cartService.addToCart(id);
        return "redirect:/index";
    }

    @PostMapping("cart/deleteProduct/{id}")
    public String cartDeleteProduct (@PathVariable Long id) {
        cartService.deleteProduct(id);
        return "redirect:/index";
    }

}
