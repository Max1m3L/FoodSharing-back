package com.maxlvshv.foodsharingback.dto;

import com.maxlvshv.foodsharingback.entity.Shop;
import com.maxlvshv.foodsharingback.entity.User;

import java.util.List;

public class AdminShopResponse {
    private User currentUser;
    private List<Shop> shop;

    public AdminShopResponse(User currentUser, List<Shop> shop) {
        this.currentUser = currentUser;
        this.shop = shop;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<Shop> getShop() {
        return shop;
    }

    public void setShop(List<Shop> shop) {
        this.shop = shop;
    }
}
