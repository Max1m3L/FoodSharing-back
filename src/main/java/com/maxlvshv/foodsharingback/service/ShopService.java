package com.maxlvshv.foodsharingback.service;

import com.maxlvshv.foodsharingback.dto.shop.CreateFoodRequest;
import com.maxlvshv.foodsharingback.dto.shop.CreateShopRequest;
import com.maxlvshv.foodsharingback.entity.Food;
import com.maxlvshv.foodsharingback.entity.Shop;
import com.maxlvshv.foodsharingback.entity.User;
import com.maxlvshv.foodsharingback.repository.FoodRepository;
import com.maxlvshv.foodsharingback.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShopService {
    private final ShopRepository shopRepo;
    private final FoodRepository foodRepo;

    @Autowired
    public ShopService(ShopRepository shopRepo, FoodRepository foodRepo) {
        this.shopRepo = shopRepo;
        this.foodRepo = foodRepo;
    }

    @Transactional
    public Shop createShop(CreateShopRequest request, User owner) {
        Shop shop = new Shop();
        shop.setName(request.name());
        shop.setDescription(request.description());
        shop.setAddress(request.address());
        shop.setContactPhone(request.contactPhone());
        shop.setOwner(owner); // Устанавливаем владельца

        return shopRepo.save(shop);
    }

    public List<Food> getActiveFoods(Long shopId) {
        return foodRepo.findByShopIdAndIsActiveTrue(shopId);
    }

    @Transactional
    public Food addFood(Long shopId, CreateFoodRequest request) {
        Shop shop = shopRepo.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        Food food = new Food();
        food.setName(request.name());
        food.setDescription(request.description());
        food.setCategory(request.category());
        food.setOriginalPrice(request.originalPrice());
        food.setDiscountPrice(request.originalPrice());
        food.setExpirationDate(LocalDateTime.now().plusHours(request.expiresDate()));
        food.setShop(shop); // Привязываем к магазину

        return foodRepo.save(food);
    }

    public List<Shop> findShopByOwner(User owner) {
        return shopRepo.findByOwner(owner);
    }

    public List<Shop> getAllShop() {
        return shopRepo.findAll();
    }

    public Shop getShop(Long id) {
        return shopRepo.findById(id).orElseThrow(null);
    }

    public void deleteShop(Long id) {
        shopRepo.deleteById(id);
    }
}
