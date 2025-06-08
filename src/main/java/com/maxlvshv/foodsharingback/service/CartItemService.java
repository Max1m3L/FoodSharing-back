package com.maxlvshv.foodsharingback.service;

import com.maxlvshv.foodsharingback.dto.shop.CreateCartItemRequest;
import com.maxlvshv.foodsharingback.entity.CartItem;
import com.maxlvshv.foodsharingback.entity.Food;
import com.maxlvshv.foodsharingback.repository.CartItemRepository;
import com.maxlvshv.foodsharingback.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maxlvshv.foodsharingback.entity.User;

import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, FoodRepository foodRepository) {
        this.cartItemRepository = cartItemRepository;

        this.foodRepository = foodRepository;
    }

    public CartItem addToCart(User user, CreateCartItemRequest request) {
        Food food = foodRepository.findById(request.food())
                .orElseThrow(() -> new RuntimeException("Food not found with id: " + request.food()));

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setFood(food);
        cartItem.setQuantity(request.quantity());

        return cartItemRepository.save(cartItem);
    }
}
