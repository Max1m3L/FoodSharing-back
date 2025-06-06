package com.maxlvshv.foodsharingback.controller;

import com.maxlvshv.foodsharingback.dto.shop.CreateShopRequest;
import com.maxlvshv.foodsharingback.dto.shop.ShopResponse;
import com.maxlvshv.foodsharingback.entity.Shop;
import com.maxlvshv.foodsharingback.entity.User;
import com.maxlvshv.foodsharingback.repository.UserRepository;
import com.maxlvshv.foodsharingback.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final UserRepository userRepository;
    private final ShopService shopService;

    @Autowired
    public OrderController(UserRepository userRepository, ShopService shopService) {
        this.userRepository = userRepository;
        this.shopService = shopService;
    }

    @PostMapping
    public ResponseEntity<ShopResponse> createShop(
            @RequestBody CreateShopRequest request,
            Principal principal
    ) {
        User owner = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Shop shop = shopService.createShop(request, owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ShopResponse(shop));
    }
}
