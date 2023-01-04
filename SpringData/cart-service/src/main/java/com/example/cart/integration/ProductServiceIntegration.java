package com.example.cart.integration;

import com.example.api.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.management.relation.RelationNotFoundException;

@Component
public class ProductServiceIntegration {

    @Autowired
    private WebClient productServiceWebIntegration;

    public ProductDTO getProductById(Long id) {
        ProductDTO productDTO = productServiceWebIntegration.get()
                .uri("products/v1/" + id)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new RelationNotFoundException("товар не найден")))
                .bodyToMono(ProductDTO.class)
                .block();

        return productDTO;
    }

}
