package com.maxlvshv.foodsharingback.repository;

import com.maxlvshv.foodsharingback.entity.FavoriteFood;
import com.maxlvshv.foodsharingback.entity.Food;
import com.maxlvshv.foodsharingback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteFoodRepository extends JpaRepository<FavoriteFood, Long> {
    List<FavoriteFood> findByUserId(Long userId);
    boolean existsByUserAndFood(User user, Food food);
}
