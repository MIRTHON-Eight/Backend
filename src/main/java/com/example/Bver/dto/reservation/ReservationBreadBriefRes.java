package com.example.Bver.dto.reservation;

import com.example.Bver.entity.ReservationBread;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class ReservationBreadBriefRes {
    private Long breadId;
    private String img;
    private String name;
    private Integer quantity;

    static public ReservationBreadBriefRes toReservationBriefRes(ReservationBread reservationBread) {
        return new ReservationBreadBriefRes(
                reservationBread.getBread().getBreadId(),
                reservationBread.getBread().getImage(),
                reservationBread.getBread().getName(),
                reservationBread.getQuantity()
        );
    }

    public ReservationBreadBriefRes(Long breadId, String img, String name, Integer quantity) {
        this.breadId = breadId;
        this.img = img;
        this.name = name;
        this.quantity = quantity;
    }
}
