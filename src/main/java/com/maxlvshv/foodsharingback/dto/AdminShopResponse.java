package com.maxlvshv.foodsharingback.dto;

import com.maxlvshv.foodsharingback.entity.Food;

import java.util.List;

public class AdminShopResponse {
    private UserDTO currentUser;
    private List<ShopDTO> shops;
    private List<Food> foods;

    public AdminShopResponse(UserDTO currentUser, List<ShopDTO> shops, List<Food> foods) {
        this.currentUser = currentUser;
        this.shops = shops;
        this.foods = foods;
    }

    public UserDTO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserDTO currentUser) {
        this.currentUser = currentUser;
    }

    public List<ShopDTO> getShops() {
        return shops;
    }

    public void setShops(List<ShopDTO> shops) {
        this.shops = shops;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
