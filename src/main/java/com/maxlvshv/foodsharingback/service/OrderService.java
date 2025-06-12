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
        // Получаем товары из корзины пользователя
        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        // Создаем новый заказ
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.REGISTERED);
        order.setFinalPrice(request.finalPrice());
        order.setDiscountPrice(request.discountPrice());

        // Добавляем товары из корзины в заказ
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order); // Устанавливаем связь с заказом
            order.getItems().add(orderItem); // Добавляем товар в заказ
        }

        // Сохраняем заказ в базе данных
        Order savedOrder = orderRepository.save(order);

        // Удаляем товары из корзины
        cartItemRepository.deleteAll(cartItems);

        return savedOrder;
    }

    public List<Order> findUserOrder(User currentUser) {
        return orderRepository.findByUser(currentUser);
    }
}
