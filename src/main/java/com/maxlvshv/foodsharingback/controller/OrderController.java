package com.maxlvshv.foodsharingback.controller;

import com.maxlvshv.foodsharingback.dto.OrderDTO;
import com.maxlvshv.foodsharingback.dto.shop.CreateOrderRequest;
import com.maxlvshv.foodsharingback.entity.Order;
import com.maxlvshv.foodsharingback.entity.User;
import com.maxlvshv.foodsharingback.repository.UserRepository;
import com.maxlvshv.foodsharingback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final UserRepository userRepository;
    private final OrderService orderService;

    @Autowired
    public OrderController(UserRepository userRepository, OrderService orderService) {
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getCart(Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> orderList = orderService.findUserOrder(currentUser);
        List<OrderDTO> cartItemDTOs = orderList.stream()
                .map(item -> new OrderDTO(item.getId(), item.getStatus(), item.getCreatedAt(),
                        item.getFinalPrice(), item.getDiscountPrice()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(cartItemDTOs);
    }

    //@GetMapping("/{orderId}")



    @PostMapping
    public ResponseEntity<?> createOrder(Principal principal,
                                         @RequestBody CreateOrderRequest request) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        orderService.createOrder(currentUser, request);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
