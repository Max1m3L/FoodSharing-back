package com.maxlvshv.foodsharingback.dto.shop;

import com.maxlvshv.foodsharingback.entity.Food;
import com.maxlvshv.foodsharingback.entity.FoodCategory;
import com.maxlvshv.foodsharingback.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateCartItemRequest(
        @NotNull Long food,
        @Positive int quantity
) {
}
