package com.maxlvshv.foodsharingback.dto.shop;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateFoodRequest(
        @NotBlank String name,
        String description,
        @Positive double originalPrice,
        @Positive int expiresInHours
) {
}
