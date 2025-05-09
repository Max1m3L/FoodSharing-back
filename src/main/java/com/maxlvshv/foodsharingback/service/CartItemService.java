package com.maxlvshv.foodsharingback.service;

import com.maxlvshv.foodsharingback.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }


}
