package com.example.gateway;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    public JwtAuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(JwtAuthFilter.Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            System.out.println("jwt auth filter apply " + isAuthMising(request));
            System.out.println(" " + request.getHeaders().containsKey("Autorization"));
            System.out.println(!isAuthMising(request));

            if (!isAuthMising(request)) {

                System.out.println("enter ");

                final String token = getAuthHeader(request);
                if (jwtUtil.isInvalid(token)) {
                    return this.onError(exchange, "Auth header invalid", HttpStatus.UNAUTHORIZED);
                }
                populateRequestWithHeaders(exchange, token);
            }
            return chain.filter(exchange);
        };
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("username", claims.getSubject())
                .build();
    }

    private boolean isAuthMising(ServerHttpRequest request) {
        if (!request.getHeaders().containsKey("Autorization"))
            return true;
        if (!request.getHeaders().getOrEmpty("Autorization").get(0).startsWith("Bearer"))
            return true;
        return false;
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Autorization").get(0).substring(7);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    public static class Config {

    }
}
