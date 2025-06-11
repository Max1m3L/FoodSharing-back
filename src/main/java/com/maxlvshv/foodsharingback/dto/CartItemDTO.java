package com.maxlvshv.foodsharingback.dto;

public class CartItemDTO {
    private Long foodId;
    private int quantity;
    private String foodName;
    private double originalPrice;
    private double discountPrice;



    public CartItemDTO(Long foodId, int quantity, String foodName, double originalPrice, double discountPrice) {
        this.foodId = foodId;
        this.quantity = quantity;
        this.foodName = foodName;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
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
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
