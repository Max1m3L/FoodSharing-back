package com.maxlvshv.foodsharingback.controller;

import com.maxlvshv.foodsharingback.dto.CartItemDTO;
import com.maxlvshv.foodsharingback.dto.UserCartResponse;
import com.maxlvshv.foodsharingback.dto.shop.CreateCartItemRequest;
import com.maxlvshv.foodsharingback.entity.CartItem;
import com.maxlvshv.foodsharingback.entity.User;
import com.maxlvshv.foodsharingback.repository.CartItemRepository;
import com.maxlvshv.foodsharingback.repository.FoodRepository;
import com.maxlvshv.foodsharingback.repository.UserRepository;
import com.maxlvshv.foodsharingback.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class CartItemController {
    private final UserRepository userRepository;
    private final CartItemService cartItemService;
    private final CartItemRepository cartItemRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public CartItemController(UserRepository userRepository, CartItemService cartItemService, CartItemRepository cartItemRepository, FoodRepository foodRepository) {
        this.userRepository = userRepository;
        this.cartItemService = cartItemService;
        this.cartItemRepository = cartItemRepository;
        this.foodRepository = foodRepository;
    }

    @GetMapping("/cart")
    public ResponseEntity<UserCartResponse> getCart(Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItemService.findUserCart(currentUser);
        List<CartItemDTO> cartItemDTOs = cartItems.stream()
                .map(item -> new CartItemDTO(item.getFood().getId(), item.getQuantity()))
                .collect(Collectors.toList());
        UserCartResponse response = new UserCartResponse(cartItemDTOs);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/shops/{shopId}/foods/cart")
    public ResponseEntity<?> addItem(Principal principal,
                                     @RequestBody CreateCartItemRequest request) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartItem cartItem = cartItemService.addToCart(currentUser, request);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/cart/increase/{cartItemId}")
    public ResponseEntity<?> increaseQuantity(@PathVariable Long cartItemId, Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        cartItemService.increaseQuantity(cartItemId, currentUser);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping("/cart/decrease/{cartItemId}")
    public ResponseEntity<?> decreaseQuantity(@PathVariable Long cartItemId, Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        cartItemService.decreaseQuantity(cartItemId, currentUser);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
