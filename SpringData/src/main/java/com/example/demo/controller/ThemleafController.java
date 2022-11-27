package com.example.demo.controller;

import com.example.demo.service.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ThemleafController {

    @Autowired
    private ProductRepositoryService productRepositoryService;

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("allProducts", productRepositoryService.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable Long id) {
        productRepositoryService.deleteById(id);
        return "redirect:/index";
    }

}
