package com.maxlvshv.foodsharingback.repository;

import com.maxlvshv.foodsharingback.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
    void deleteByUserIdAndFoodId(Long userId, Long foodId);
}
