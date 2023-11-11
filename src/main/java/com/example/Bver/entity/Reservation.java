package com.example.Bver.entity;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "Reservation")

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

//    public void setQuantity(Integer quantity) {
//    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "breadId")
//    private Bread bread;
//
//    @Column(name = "quantity")
//    private Integer quantity;



}
