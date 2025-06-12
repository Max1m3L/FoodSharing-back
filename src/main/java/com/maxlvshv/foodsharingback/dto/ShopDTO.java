package com.maxlvshv.foodsharingback.dto;

import com.maxlvshv.foodsharingback.entity.Shop;
import com.maxlvshv.foodsharingback.entity.ShopType;

public class ShopDTO {
    private Long id;
    private String name;
    private String description;
    private ShopType type;
    private String address;
    private String contactPhone;

    public ShopDTO(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.description = shop.getDescription();
        this.type = shop.getType();
        this.address = shop.getAddress();
        this.contactPhone = shop.getContactPhone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShopType getType() {
        return type;
    }

    public void setType(ShopType type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
