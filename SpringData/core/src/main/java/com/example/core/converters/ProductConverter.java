package com.example.core.converters;

import com.example.api.ProductDTO;
import com.example.core.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    public ProductDTO entityToDto(Product product) {
        return new ProductDTO(product.getId(), product.getTitle(), product.getCost());
    }

    public Product dtoToEntity(ProductDTO productDto) {
        Product p = new Product();
        p.setId(productDto.getId());
        p.setTitle(productDto.getTitle());
        p.setCost(productDto.getCost());
        return p;
    }
}
