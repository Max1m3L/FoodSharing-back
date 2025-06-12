package com.maxlvshv.foodsharingback.dto;

import com.maxlvshv.foodsharingback.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTOforDet {
    private Long id;
    private Long userId;
    private List<OrderItemDTO> items;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private int finalPrice;
    private int discountPrice;

    public OrderDTOforDet() {

    }

    public OrderDTOforDet(Long id, Long userId, List<OrderItemDTO> items, OrderStatus status, LocalDateTime createdAt, int finalPrice, int discountPrice) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.status = status;
        this.createdAt = createdAt;
        this.finalPrice = finalPrice;
        this.discountPrice = discountPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }
}
