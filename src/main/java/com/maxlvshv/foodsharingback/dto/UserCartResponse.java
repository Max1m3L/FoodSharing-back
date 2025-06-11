package com.maxlvshv.foodsharingback.dto;

import java.util.List;

public class UserCartResponse {
    private List<CartItemDTO> cartItems;

    public UserCartResponse(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
