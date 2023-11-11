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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member memberId;

    static public Reservation toEntity(Member memberId) {
        return new Reservation(memberId);
    }
    public Reservation(Member memberId) {
        this.memberId = memberId;
    }
}
