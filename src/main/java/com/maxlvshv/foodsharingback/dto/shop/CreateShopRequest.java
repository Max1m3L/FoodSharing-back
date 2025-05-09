package com.maxlvshv.foodsharingback.dto.shop;

import jakarta.validation.constraints.NotBlank;

public record CreateShopRequest(
        @NotBlank String name,
        String description,
        @NotBlank String address)
{}
