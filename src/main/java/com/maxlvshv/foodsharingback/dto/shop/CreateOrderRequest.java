package com.maxlvshv.foodsharingback.dto.shop;

import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
        @NotNull int finalPrice,
        @NotNull int discountPrice
) {
}
