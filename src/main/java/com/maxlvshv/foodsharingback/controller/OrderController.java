package com.maxlvshv.foodsharingback.controller;

import com.maxlvshv.foodsharingback.dto.OrderDTO;
import com.maxlvshv.foodsharingback.dto.OrderDTOforDet;
import com.maxlvshv.foodsharingback.dto.OrderItemDTO;
import com.maxlvshv.foodsharingback.dto.shop.CreateOrderRequest;
import com.maxlvshv.foodsharingback.entity.Order;
import com.maxlvshv.foodsharingback.entity.OrderStatus;
import com.maxlvshv.foodsharingback.entity.User;
import com.maxlvshv.foodsharingback.repository.OrderRepository;
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
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(UserRepository userRepository, OrderService orderService, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

//    @GetMapping
//    public ResponseEntity<List<OrderDTO>> getCart(Principal principal) {
//        User currentUser = userRepository.findByUsername(principal.getName())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        List<Order> orderList = orderService.findUserOrder(currentUser);
//        List<OrderDTO> cartItemDTOs = orderList.stream()
//                .map(item -> new OrderDTO(item.getId(), item.getUser().getId(), item.getItems(),item.getStatus(),
//                        item.getCreatedAt(), item.getFinalPrice(), item.getDiscountPrice()))
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(cartItemDTOs);
//    }
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getCart(Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> orderList = orderService.findUserOrder(currentUser);
        List<OrderDTO> cartItemDTOs = orderList.stream()
                .map(order -> {
                    List<OrderItemDTO> orderItemDTOs = order.getItems().stream()
                            .map(item -> new OrderItemDTO(item.getId(), item.getQuantity(), item.getPriceAtPurchase()))
                            .collect(Collectors.toList());

                    return new OrderDTO(order.getId(), order.getUser().getId(), orderItemDTOs, order.getStatus(),
                            order.getCreatedAt(), order.getFinalPrice(), order.getDiscountPrice());
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(cartItemDTOs);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTOforDet> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.findOrderById(orderId);
        if (order != null) {
            OrderDTOforDet orderDTO = new OrderDTOforDet();
            orderDTO.setId(order.getId());
            orderDTO.setUserId(order.getUser().getId());
            orderDTO.setStatus(order.getStatus());
            orderDTO.setCreatedAt(order.getCreatedAt());
            orderDTO.setFinalPrice(order.getFinalPrice());
            orderDTO.setDiscountPrice(order.getDiscountPrice());

            List<OrderItemDTO> orderItems = order.getItems().stream().map(item -> {
                OrderItemDTO itemDTO = new OrderItemDTO();
                itemDTO.setId(item.getId());
                itemDTO.setFoodId(item.getFood().getId());
                itemDTO.setQuantity(item.getQuantity());
                itemDTO.setPriceAtPurchase(item.getPriceAtPurchase());
                return itemDTO;
            }).collect(Collectors.toList());

            orderDTO.setItems(orderItems);
            return ResponseEntity.ok(orderDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(Principal principal,
                                         @RequestBody CreateOrderRequest request) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        orderService.createOrder(currentUser, request);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/{orderId}/process")
    public ResponseEntity<?> statusINPROCESS(@PathVariable Long orderId) {
        Order order = orderService.findOrderById(orderId);
        order.setStatus(OrderStatus.INPROCESS);
        orderRepository.save(order);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @PostMapping("/{orderId}/ready")
    public ResponseEntity<?> statusREADY(@PathVariable Long orderId) {
        Order order = orderService.findOrderById(orderId);
        order.setStatus(OrderStatus.READY);
        orderRepository.save(order);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @PostMapping("/{orderId}/received")
    public ResponseEntity<?> statusRECEIVED(@PathVariable Long orderId) {
        Order order = orderService.findOrderById(orderId);
        order.setStatus(OrderStatus.RECEIVED);
        orderRepository.save(order);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
