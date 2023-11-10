package com.example.Bver.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "BREAD")
public class Bread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long breadId;

    @Column(nullable = false)
    private String name;

    @Column
    private int price;

    @Column
    private int discountedPrice;

    @Column
    private String image;

    @Column(nullable = false)
    private int quantity;

    @Column
    private String nutritional;

    @Column
    private String allergen;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private Store storeId;
}
