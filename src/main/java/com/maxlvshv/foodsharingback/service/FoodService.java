package com.maxlvshv.foodsharingback.service;

import com.maxlvshv.foodsharingback.entity.Food;
import com.maxlvshv.foodsharingback.entity.Shop;
import com.maxlvshv.foodsharingback.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getFoods() {
        return foodRepository.findAll();
    }

    public List<Food> getByShop(Shop shop) {
        return foodRepository.findAllByShop(shop);
    }
}
