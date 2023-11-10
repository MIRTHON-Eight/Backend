package com.example.Bver.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "STORE")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String operatingHour;

    @Column(nullable = false)
    private String businessNum;

    @Column(columnDefinition = "text")
    private String storePhoto;

    @Column(columnDefinition = "text")
    private String storeLogo;
}
