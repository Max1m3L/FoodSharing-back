package com.maxlvshv.foodsharingback.dto;

import com.maxlvshv.foodsharingback.entity.OrderStatus;

import java.time.LocalDateTime;

public class OrderDTO {
    private Long id;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private int finalPrice;
    private int discountPrice;

    public OrderDTO(Long id, OrderStatus status, LocalDateTime createdAt, int finalPrice, int discountPrice) {
        this.id = id;
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
