package com.example.demo.controller;

import com.example.demo.service.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ThemleafController {

    @Autowired
    private ProductRepositoryService productRepositoryService;

    @GetMapping("/index")
    public String showUserList(@RequestParam(required = false) Integer minCost,
                               @RequestParam(required = false) Integer maxCost,
                               @RequestParam(required = false) String title,
                               Model model) {
        System.out.println(minCost);
        System.out.println(maxCost);
        System.out.println(title);
        model.addAttribute("allProducts",
                productRepositoryService.findAll(minCost, maxCost, title));
        return "index";
    }

//<form action="#" th:action="@{'/{id}'(id=${p.id})}" method="post">
//    не могу тут указать метод delete в инете написано что тут может быть только пост или гет
    @PostMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        productRepositoryService.deleteById(id);
        return "redirect:/index";
    }

    @PostMapping("sort")
    public String showSortedList(@RequestParam(required = false) Long minCost,
                                 @RequestParam(required = false) Long maxCost, Model model) {

        model.addAttribute("allProducts",
                productRepositoryService.findByCostBetween(minCost, maxCost));
        return "index";
    }

}
