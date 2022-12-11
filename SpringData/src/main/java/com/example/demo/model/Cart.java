package com.example.demo.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<ProductDTO> productDTOList = new ArrayList<>();

    public void addToCart (ProductDTO productDTO){
        productDTOList.add(productDTO);
    }

    public List<ProductDTO> findAllCartProducts () {
        return productDTOList;
    }
}
