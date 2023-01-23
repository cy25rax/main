package com.example.cart.services;

import com.example.api.ProductDTO;
import com.example.cart.integration.ProductServiceIntegration;
import com.example.cart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class CartService {

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    private ProductServiceIntegration productServiceIntegration;

    public void addQuantity(Long id, int quantity, String uuid) {
        execute(uuid, cart -> cart.addQuantity(id, quantity));
    }
    
    public Cart getCurrentCart(String uuid, String username) {
        String targetUuid = cartPrefix + uuid;
        String targetUser = cartPrefix + username;
        if (redisTemplate.hasKey(targetUuid) && redisTemplate.hasKey(targetUser)) {
            Cart userCart = (Cart) redisTemplate.opsForValue().get(targetUser);
            Cart uuidCart = (Cart) redisTemplate.opsForValue().get(targetUuid);
            Cart result = Cart.sumCarts(userCart, uuidCart);
            redisTemplate.opsForValue().set(targetUser, result);
            uuidCart.eraseCart();
            redisTemplate.opsForValue().set(targetUuid, uuidCart);
            return result;
        }
        if (!redisTemplate.hasKey(targetUuid)) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }
    
    public void add(String uuid, Long productId) {
        ProductDTO product = productServiceIntegration.getProductById(productId);
        execute(uuid, cart -> cart.addToCart(product));
    }
    
    public void remove(String uuid, Long productId) {
        execute(uuid, cart -> cart.deleteProductFromCart(productId));
    }
    
    public void clear(String uuid) {
        execute(uuid, Cart::eraseCart);
    }
    
    private void execute(String uuid, Consumer<Cart> operation) {
        Cart cart = getCurrentCart(uuid, null);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartPrefix + uuid, cart);
    }

}
