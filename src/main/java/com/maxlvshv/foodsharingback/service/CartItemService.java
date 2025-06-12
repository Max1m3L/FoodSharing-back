package com.maxlvshv.foodsharingback.service;

import com.maxlvshv.foodsharingback.dto.shop.CreateCartItemRequest;
import com.maxlvshv.foodsharingback.entity.CartItem;
import com.maxlvshv.foodsharingback.entity.Food;
import com.maxlvshv.foodsharingback.repository.CartItemRepository;
import com.maxlvshv.foodsharingback.repository.FoodRepository;
import com.maxlvshv.foodsharingback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maxlvshv.foodsharingback.entity.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, UserRepository userRepository, FoodRepository foodRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
    }

    public CartItem addToCart(User user, CreateCartItemRequest request) {
        Food food = foodRepository.findById(request.food())
                .orElseThrow(() -> new RuntimeException("Food not found with id: " + request.food()));

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setFood(food);
        cartItem.setQuantity(1);

        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> findUserCart(User user) {
        return cartItemRepository.findByUser(user);
    }

    public CartItem increaseQuantity(Long cartItemId, User user) {
        CartItem cartItem = cartItemRepository.findByIdAndUser(cartItemId, user)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        return cartItemRepository.save(cartItem);
    }

    public CartItem decreaseQuantity(Long cartItemId, User user) {
        CartItem cartItem = cartItemRepository.findByIdAndUser(cartItemId, user)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            return cartItemRepository.save(cartItem);
        } else {
            cartItemRepository.delete(cartItem);
        }
        return null;
    }

    public void removeFromCart(Long cartItemId, User user) {
        CartItem cartItem = cartItemRepository.findByIdAndUser(cartItemId, user)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItemRepository.delete(cartItem);
    }
}
