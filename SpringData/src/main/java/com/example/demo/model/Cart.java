package com.example.demo.model;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class Cart {
    private List<ProductDTO> productDTOList;

    @PostConstruct
    public void init () {
        productDTOList = new ArrayList<>();
    }

    public void addToCart (ProductDTO productDTO){
        productDTOList.add(productDTO);
    }

    public List<ProductDTO> findAllCartProducts () {
        return productDTOList;
    }

    public void deleteProductFromCart(Long id) {
        for (ProductDTO productDTO: productDTOList) {
            if (Objects.equals(productDTO.getId(), id)) {
                productDTOList.remove(productDTO);
                break;
            }
        }
    }
}
