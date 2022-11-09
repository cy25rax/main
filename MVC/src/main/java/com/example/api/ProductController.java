package com.example.api;

import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Product getProduct(@PathVariable int id) {

    var product = productService.findById(id);

    return product;
  }

  @GetMapping
  public String getProducts(Model model) {
    var products = productService.findAll();
    model.addAttribute("productList", products);
    return "products";
  }

  @PostMapping
  public String addProduct(@RequestParam String name, @RequestParam int cost, Model model) {
    Product p = new Product(name, cost);
    productService.addProduct(p);
//    products.add(p);

    var products = productService.findAll();
    model.addAttribute("productList", products);
    return "products";
  }

}
