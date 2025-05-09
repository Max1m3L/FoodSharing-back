package com.maxlvshv.foodsharingback.controller;

import com.maxlvshv.foodsharingback.entity.User;
import com.maxlvshv.foodsharingback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
public class CartItemController {
    private final UserRepository userRepository;

    @Autowired
    public CartItemController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> addItem(Principal principal) {
        User currentUser = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(currentUser);
    }
}
