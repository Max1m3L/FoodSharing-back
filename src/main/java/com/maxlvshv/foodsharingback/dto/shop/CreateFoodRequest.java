package com.maxlvshv.foodsharingback.dto.shop;

import com.maxlvshv.foodsharingback.entity.FoodCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateFoodRequest(
        @NotBlank String name,
        String description,
        @NotNull FoodCategory category,
        @Positive double originalPrice,
        @Positive double discountPrice,
        @Positive int expiresDate
) {
}
