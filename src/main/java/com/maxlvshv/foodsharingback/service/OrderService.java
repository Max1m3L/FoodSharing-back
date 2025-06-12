package com.maxlvshv.foodsharingback.service;

import com.maxlvshv.foodsharingback.dto.shop.CreateOrderRequest;
import com.maxlvshv.foodsharingback.entity.CartItem;
import com.maxlvshv.foodsharingback.entity.Order;
import com.maxlvshv.foodsharingback.entity.OrderItem;
import com.maxlvshv.foodsharingback.entity.OrderStatus;
import com.maxlvshv.foodsharingback.repository.CartItemRepository;
import com.maxlvshv.foodsharingback.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maxlvshv.foodsharingback.entity.User;

import java.util.List;
import java.util.Optional;


@Service
public class OrderService {
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(CartItemRepository cartItemRepository, OrderRepository orderRepository) {
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(User user, CreateOrderRequest request) {
        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.REGISTERED);
        order.setFinalPrice(request.finalPrice());
        order.setDiscountPrice(request.discountPrice());

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItem.setPriceAtPurchase(request.discountPrice());
            order.getItems().add(orderItem);
        }

        Order savedOrder = orderRepository.save(order);

        cartItemRepository.deleteAll(cartItems);

        return savedOrder;
    }

    public List<Order> findUserOrder(User currentUser) {
        return orderRepository.findByUser(currentUser);
    }

    public Order findOrderById(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        return orderOptional.orElse(null); // Возвращаем заказ или null, если не найден
    }
}
