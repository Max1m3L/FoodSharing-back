package com.maxlvshv.foodsharingback.repository;

import com.maxlvshv.foodsharingback.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByShopIdAndIsActiveTrue(Long shopId);
    List<Food> findByExpirationDateBetween(LocalDateTime start, LocalDateTime end);
}
