package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.ProductDTO;
import com.example.demo.service.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class ThymeleafController {

    @Autowired
    private ProductRepositoryService productRepositoryService;
    @Autowired
    private Cart cart;

    @GetMapping("/index")
    public String showUserList(@RequestParam(required = false) Integer minCost,
                               @RequestParam(required = false) Integer maxCost,
                               @RequestParam(required = false) String title,
                               Model model) {
        model.addAttribute("allProducts",
                productRepositoryService.findAll(minCost, maxCost, title));
        model.addAttribute("cart",
                cart.findAllCartProducts());

        return "index1";
    }

    @PostMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        System.out.println(id);
        productRepositoryService.deleteById(id);
        return "redirect:/index";
    }

    @PostMapping("cartAdd/{id}")
    public String cartAdd(@PathVariable Long id) {
        Product product = productRepositoryService.getReferenceById(id);
        ProductDTO productDTO = new ProductDTO(product);
        cart.addToCart(productDTO);
        return "redirect:/index";
    }

    @PostMapping("cartDeleteProduct/{id}")
    public String cartDeleteProduct (@PathVariable Long id) {
        for (ProductDTO productDTO: cart.findAllCartProducts()) {
            if (Objects.equals(productDTO.getId(), id)) {
                cart.findAllCartProducts().remove(productDTO);
                break;
            }
        }
        return "redirect:/index";
    }

}
