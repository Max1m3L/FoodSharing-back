package com.maxlvshv.foodsharingback.dto.shop;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateCartItemRequest(
        @NotNull Long food
) {
}
