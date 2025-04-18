package com.maxlvshv.foodsharingback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_favorites")
@IdClass(FavoriteFoodId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteFood {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @Column(nullable = false)
    private LocalDateTime addedAt = LocalDateTime.now();
}

// Класс для составного ключа
class FavoriteFoodId implements Serializable {
    private Long user;
    private Long food;
}
