package com.maxlvshv.foodsharingback.dto.shop;

import com.maxlvshv.foodsharingback.entity.Shop;

public record ShopResponse(
        Long id,
        String name,
        String address,
        Long ownerId
) {
    public ShopResponse(Shop shop) {
        this(shop.getId(), shop.getName(), shop.getAddress(), shop.getOwner().getId());
    }
}
