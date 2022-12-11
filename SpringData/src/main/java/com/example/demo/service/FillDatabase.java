package com.example.demo.service;

import com.example.demo.interfaces.ProductRepository;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FillDatabase {

    @Autowired
    ProductRepository productRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void fillBase(){
//        productRepository.save(new Product("corolla", 10L, "3s"));
//        productRepository.save(new Product("rx", 12L, "3s"));
//        productRepository.save(new Product("crown", 20L, "1jz"));
//        productRepository.save(new Product("celsior", 25L, "2uz"));
//        productRepository.save(new Product("century", 30L, "1gz"));
//        productRepository.save(new Product("markII", 18L, "1g"));
//        productRepository.save(new Product("chaser", 19L, "2jz"));
//        productRepository.save(new Product("alphard", 28L, "2gr"));
//        productRepository.save(new Product("tundra", 25L, "3ur"));
//        productRepository.save(new Product("land cruiser", 21L, "1ur"));
//        productRepository.save(new Product("majesta", 27L, "1uz"));

//        Role role = new Role();
//        role.setName("ROLE_USER");
//        Role role1 = new Role();
//        role1.setName("ROLE_ADMIN");
//
//        roleRepository.save(role);
//        roleRepository.save(role1);
//
//        User user = new User();
//        user.setName("1");
//        user.setPassword("$2a$12$CpNDFscYwB.gJFLhnsfo8eIR4xgdalah9IdUn4E50SkuyTOqnVZRq");
//        user.setPhone("1234567890");
//        user.setRoles(List.of(role));
//
//        User user1 = new User();
//        user.setName("2");
//        user.setPassword("$2a$12$vijK/SsfAJKPdMR45BLFeOZgvO6DVYhKSVvMXGp8pbjfRAmWGTzc2");
//        user.setPhone("0987654321");
//        user.setRoles(Arrays.asList(role, role1));
//
//        userRepository.save(user);
//        userRepository.save(user1);
//
//        System.out.println(roleRepository.findAll());
//        System.out.println(userRepository.findAll());
    }

}
