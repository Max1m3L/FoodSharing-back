package com.maxlvshv.foodsharingback.repository;

import com.maxlvshv.foodsharingback.entity.CartItem;
import com.maxlvshv.foodsharingback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);

    void deleteByUserIdAndFoodId(Long userId, Long foodId);

    List<CartItem> findByUser(User user);

    Optional<CartItem> findByIdAndUser(Long cartItemId, User user);
}
