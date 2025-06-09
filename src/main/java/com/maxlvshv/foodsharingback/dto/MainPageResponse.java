package com.maxlvshv.foodsharingback.dto;

import com.maxlvshv.foodsharingback.entity.Food;
import com.maxlvshv.foodsharingback.entity.Shop;

import java.util.List;
import com.maxlvshv.foodsharingback.entity.User;

public class MainPageResponse {
    private User currentUser;
    private List<Shop> shops;
    private List<Food> food;

    // Конструкторы, геттеры и сеттеры
    public MainPageResponse(User currentUser, List<Shop> shops, List<Food> food) {
        this.currentUser = currentUser;
        this.shops = shops;
        this.food = food;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public List<Food> getFood() {
        return food;
    }
}

