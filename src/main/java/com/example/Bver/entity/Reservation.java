package com.example.Bver.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member memberId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store storeId;
    @Column
    private LocalDateTime createdAt;

    static public Reservation toEntity(Member memberId, Store storeId) {
        return new Reservation(memberId, storeId);
    }
    public Reservation(Member memberId, Store storeId) {
        this.memberId = memberId;
        this.storeId = storeId;
        this.createdAt = LocalDateTime.now();
    }
}
