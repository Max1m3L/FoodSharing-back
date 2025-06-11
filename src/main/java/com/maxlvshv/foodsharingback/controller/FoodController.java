package com.maxlvshv.foodsharingback.controller;

import com.maxlvshv.foodsharingback.entity.Food;
import com.maxlvshv.foodsharingback.entity.Shop;
import com.maxlvshv.foodsharingback.repository.FoodRepository;
import com.maxlvshv.foodsharingback.repository.ShopRepository;
import com.maxlvshv.foodsharingback.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class FoodController {
    private final FoodService foodService;
    private final FoodRepository foodRepository;

    @Autowired
    public FoodController(FoodService foodService, FoodRepository foodRepository) {
        this.foodService = foodService;
        this.foodRepository = foodRepository;
    }

    @PostMapping("/food/toggleActive/{foodId}")
    public ResponseEntity<Food> toggleActive(@PathVariable Long foodId) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        food.setActive(!food.isActive());
        foodRepository.save(food);

        return ResponseEntity.ok(food);
    }

    @GetMapping
    public ResponseEntity<List<Food>> getFoods() {
        return ResponseEntity.ok(foodService.getFoods());
    }
}
