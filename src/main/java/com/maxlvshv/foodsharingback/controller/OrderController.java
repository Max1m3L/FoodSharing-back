package com.maxlvshv.foodsharingback.controller;

import com.maxlvshv.foodsharingback.entity.Order;
import com.maxlvshv.foodsharingback.entity.User;
import com.maxlvshv.foodsharingback.repository.UserRepository;
import com.maxlvshv.foodsharingback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping
public class OrderController {
    private final UserRepository userRepository;
    private final OrderService orderService;

    @Autowired
    public OrderController(UserRepository userRepository, OrderService orderService) {
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    //Бесконечный JSON!!!
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = orderService.createOrder(currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
