package com.example.core.integration;

import com.example.api.CartDto;
import com.example.api.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@PropertySource("classpath:links.properties")
public class CartServiceIntegration {

    @Autowired
    private RestTemplate restTemplate;

//    @Value("${cartServiceListCartItems}")
//    private String cartServiceListCartItems;
    @Value("${getCartLink}")
    private String getCartLink;

    @Value("${eraseCart}")
    private String eraseCart;

//    public List<CartItemDto> findAllCartItems() {
//        ResponseEntity<List<CartItemDto>> rateResponse =
//                restTemplate.exchange(
//                        cartServiceListCartItems,
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<>() {});
//        List<CartItemDto> rates = rateResponse.getBody();
//        return rates;
//    }

    public CartDto getCart() {
        return restTemplate.getForObject(getCartLink, CartDto.class);
    }

    public void clearCart() {
        restTemplate.delete(eraseCart);
    }
}
