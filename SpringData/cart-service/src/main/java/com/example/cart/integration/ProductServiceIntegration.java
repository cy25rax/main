package com.example.cart.integration;

import com.example.api.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@PropertySource("classpath:links.properties")
public class ProductServiceIntegration {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${getProductFromCore}")
    private String getProductFromCore;

    public ProductDTO getProductById(Long id) {
        return restTemplate.getForObject(
                getProductFromCore + id,
                ProductDTO.class);
    }

}
