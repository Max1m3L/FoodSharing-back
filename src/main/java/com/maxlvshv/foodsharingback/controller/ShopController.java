package com.maxlvshv.foodsharingback.controller;

import com.maxlvshv.foodsharingback.dto.AdminShopResponse;
import com.maxlvshv.foodsharingback.dto.MainPageResponse;
import com.maxlvshv.foodsharingback.dto.shop.CreateFoodRequest;
import com.maxlvshv.foodsharingback.dto.shop.CreateShopRequest;
import com.maxlvshv.foodsharingback.dto.shop.ShopResponse;
import com.maxlvshv.foodsharingback.entity.Food;
import com.maxlvshv.foodsharingback.entity.Role;
import com.maxlvshv.foodsharingback.entity.Shop;
import com.maxlvshv.foodsharingback.entity.User;
import com.maxlvshv.foodsharingback.repository.UserRepository;
import com.maxlvshv.foodsharingback.service.FoodService;
import com.maxlvshv.foodsharingback.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {
    private final ShopService shopService;
    private final UserRepository userRepository;
    private final FoodService foodService;

    @Autowired
    public ShopController(ShopService shopService, UserRepository userRepository, FoodService foodService) {
        this.shopService = shopService;
        this.userRepository = userRepository;
        this.foodService = foodService;
    }

    // Создание магазина
    @PostMapping
    public ResponseEntity<ShopResponse> createShop(
            @RequestBody CreateShopRequest request,
            Principal principal
    ) {
        User owner = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Shop shop = shopService.createShop(request, owner);
        owner.setRole(Role.ROLE_ADMIN);
        userRepository.save(owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ShopResponse(shop));
    }

    // Главная страница - выводит все магазины и еду
    @GetMapping
    public ResponseEntity<MainPageResponse> getShops(Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Shop> shops = shopService.getAllShop();
        List<Food> products = foodService.getFoods();
        MainPageResponse response = new MainPageResponse(currentUser, shops, products);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShop(@PathVariable Long id) {
        Shop shop = shopService.getShop(id);
        return shop != null ? ResponseEntity.ok(shop) : ResponseEntity.notFound().build();
    }

    @GetMapping("/admin")
    public ResponseEntity<AdminShopResponse> getShopByAdmin(Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Shop> shop = shopService.findShopByOwner(currentUser);
        AdminShopResponse response = new AdminShopResponse(currentUser, shop);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        shopService.deleteShop(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{shopId}/foods")
//    @PreAuthorize("@shopSecurityService.isShopOwner(#shopId, principal.name)")
    public ResponseEntity<?> addFoodToShop(
            @PathVariable Long shopId,
            @RequestBody CreateFoodRequest request
    ) {
        Food food = shopService.addFood(shopId, request);
        return ResponseEntity.ok(request);
    }
}
