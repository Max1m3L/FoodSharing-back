package com.maxlvshv.foodsharingback.controller;

import com.maxlvshv.foodsharingback.dto.shop.CreateFoodRequest;
import com.maxlvshv.foodsharingback.dto.shop.CreateShopRequest;
import com.maxlvshv.foodsharingback.dto.shop.ShopResponse;
import com.maxlvshv.foodsharingback.entity.Food;
import com.maxlvshv.foodsharingback.entity.Shop;
import com.maxlvshv.foodsharingback.entity.User;
import com.maxlvshv.foodsharingback.repository.UserRepository;
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

    @Autowired
    public ShopController(ShopService shopService, UserRepository userRepository) {
        this.shopService = shopService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<ShopResponse> createShop(
            @RequestBody CreateShopRequest request,
            Principal principal // Автоматически содержит email текущего пользователя
    ) {
        User owner = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Shop shop = shopService.createShop(request, owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ShopResponse(shop));
    }

    @GetMapping
    public ResponseEntity<List<Shop>> getShops() {
        return ResponseEntity.ok(shopService.getAllShop());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShop(@PathVariable Long id) {
        Shop shop = shopService.getShop(id);
        return shop != null ? ResponseEntity.ok(shop) : ResponseEntity.notFound().build();
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
