package com.maxlvshv.foodsharingback.controller;

import com.maxlvshv.foodsharingback.dto.shop.CreateCartItemRequest;
import com.maxlvshv.foodsharingback.dto.shop.CreateFoodRequest;
import com.maxlvshv.foodsharingback.entity.CartItem;
import com.maxlvshv.foodsharingback.entity.Food;
import com.maxlvshv.foodsharingback.entity.User;
import com.maxlvshv.foodsharingback.repository.FoodRepository;
import com.maxlvshv.foodsharingback.repository.UserRepository;
import com.maxlvshv.foodsharingback.service.CartItemService;
import com.maxlvshv.foodsharingback.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping
public class CartItemController {
    private final UserRepository userRepository;
    private final CartItemService cartItemService;
    private final FoodRepository foodRepository;

    @Autowired
    public CartItemController(UserRepository userRepository, CartItemService cartItemService, FoodRepository foodRepository) {
        this.userRepository = userRepository;
        this.cartItemService = cartItemService;
        this.foodRepository = foodRepository;
    }

    @GetMapping("/cart")
    public ResponseEntity<?> getCart() {
        return ResponseEntity.ok(3);
    }

    @PostMapping("/shops/{shopId}/foods/cart")
    public ResponseEntity<?> addItem(Principal principal,
                                     @RequestBody CreateCartItemRequest request) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartItem cartItem = cartItemService.addToCart(currentUser, request);
        return ResponseEntity.ok(request);
    }
}
