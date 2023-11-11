package com.example.Bver.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class ReservationBread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breadId")
    private Bread bread;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reservationId")
    private Reservation reservationId;

    @Column(name = "quantity")
    private Integer quantity;

    static public ReservationBread toEntity(Bread bread, Reservation reservationId, Integer quantity) {
        return new ReservationBread(bread, reservationId,quantity);
    }

    public ReservationBread(Bread bread, Reservation reservationId, Integer quantity) {
        this.bread = bread;
        this.reservationId = reservationId;
        this.quantity = quantity;
    }
}
