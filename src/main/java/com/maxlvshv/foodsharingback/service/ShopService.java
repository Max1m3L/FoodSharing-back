package com.maxlvshv.foodsharingback.service;

import com.maxlvshv.foodsharingback.entity.Food;
import com.maxlvshv.foodsharingback.entity.Shop;
import com.maxlvshv.foodsharingback.repository.FoodRepository;
import com.maxlvshv.foodsharingback.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepo;
    private final FoodRepository foodRepo;

    public List<Food> getActiveFoods(Long shopId) {
        return foodRepo.findByShopIdAndIsActiveTrue(shopId);
    }

    public Food addFoodToShop(Long shopId, Food food) {
        Shop shop = shopRepo.findById(shopId).orElseThrow();
        food.setShop(shop);
        return foodRepo.save(food);
    }
}
