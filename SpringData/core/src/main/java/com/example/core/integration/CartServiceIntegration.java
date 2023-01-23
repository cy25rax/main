package com.example.core.integration;

import com.example.api.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.management.relation.RelationNotFoundException;

@Component
public class CartServiceIntegration {

    @Autowired
    private WebClient cartServiceWebIntegration;

    public CartDto getCart(String userName) {
        CartDto cartDto = cartServiceWebIntegration.get()
                .uri("cart/v1/0")
                .header("username", userName)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new RelationNotFoundException("товар не найден")))
                .bodyToMono(CartDto.class)
                .block();

        return cartDto;
    }

    public void clearCart(String userName) {
        cartServiceWebIntegration.get()
                .uri("cart/v1/0/clear")
                .header("username", userName)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
