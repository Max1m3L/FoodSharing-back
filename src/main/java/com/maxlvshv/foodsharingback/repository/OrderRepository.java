package com.maxlvshv.foodsharingback.repository;

import com.maxlvshv.foodsharingback.entity.Order;
import com.maxlvshv.foodsharingback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);

    List<Order> findByUser(User currentUser);
}
