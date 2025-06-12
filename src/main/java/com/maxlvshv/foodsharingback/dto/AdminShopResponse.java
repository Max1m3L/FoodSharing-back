package com.maxlvshv.foodsharingback.dto;

import com.maxlvshv.foodsharingback.entity.Shop;
import com.maxlvshv.foodsharingback.entity.User;

import java.util.List;

public class AdminShopResponse {
    private UserDTO currentUser;
    private List<ShopDTO> shops;

    public AdminShopResponse(UserDTO currentUser, List<ShopDTO> shops) {
        this.currentUser = currentUser;
        this.shops = shops;
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
}
