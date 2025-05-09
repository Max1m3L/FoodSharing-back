package com.maxlvshv.foodsharingback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shops")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ShopType type;

    @Column(nullable = false)
    private String address;

    private String contactPhone;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Food> foods = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
