package com.maxlvshv.foodsharingback.dto;

import com.maxlvshv.foodsharingback.entity.OrderItem;

public class OrderItemDTO {
    private Long id;
    private Long foodId;
    private int quantity;
    private double priceAtPurchase;

    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.foodId = orderItem.getFood().getId(); // Получаем ID еды
        this.quantity = orderItem.getQuantity();
        this.priceAtPurchase = orderItem.getPriceAtPurchase();
    }

    public OrderItemDTO() {}

    public OrderItemDTO(Long id, int quantity, double priceAtPurchase) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }
}
