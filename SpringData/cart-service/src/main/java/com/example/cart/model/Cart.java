package com.example.cart.model;

import com.example.api.ProductDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Cart {
    private List<CartItem> cartItemList;
    private BigDecimal totalCost;

    public Cart() {
        this.cartItemList = new ArrayList<>();
        this.totalCost = new BigDecimal(0);
    }

    public void addToCart (ProductDTO productDTO){
        for (CartItem cartItem:cartItemList) {
            if (cartItem.getProductId().equals(productDTO.getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                recalculateTotalCost();
                return;
            }
        }
        CartItem cartItem = new CartItem(productDTO.getId(),
                productDTO.getTitle(),
                1,
                productDTO.getCost(),
                productDTO.getCost());
        cartItemList.add(cartItem);
        recalculateTotalCost();
    }

    private void recalculateTotalCost() {
        totalCost = BigDecimal.valueOf(0);
        for (CartItem cartItem:cartItemList) {
            cartItem.setCost(cartItem.getCostPerProduct()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            totalCost = totalCost.add(cartItem.getCost());
        }
    }

    public void deleteProductFromCart(Long id) {
        for (CartItem cartItem:cartItemList) {
            if (Objects.equals(cartItem.getProductId(), id)) {
                cartItemList.remove(cartItem);
                recalculateTotalCost();
                break;
            }
        }
    }

    public void eraseCart() {
        cartItemList = new ArrayList<>();
        totalCost = BigDecimal.valueOf(0);
    }

    public void addQuantity(Long id, int quantity) {
        for (CartItem cartItem:cartItemList) {
            if (Objects.equals(cartItem.getProductId(), id)) {
                if (cartItem.getQuantity() == 1 && quantity == -1) return;
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                recalculateTotalCost();
                break;
            }
        }
    }
}
