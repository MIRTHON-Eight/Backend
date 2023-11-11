package com.example.Bver.dto.reservation;

import com.example.Bver.entity.Reservation;
import com.example.Bver.entity.ReservationBread;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class ReservationBriefRes {
    private Long reservationId;

    private String storeName;

    private List<ReservationBreadBriefRes> breadsInfo;
    private LocalDate createdAt;

    static public ReservationBriefRes toReservationBriefRes(Reservation reservation) {
        return new ReservationBriefRes(
                reservation.getId(),
                reservation.getCreatedAt()
        );
    }

    public ReservationBriefRes(Long reservationId, LocalDateTime createdAt) {
        this.reservationId = reservationId;
        this.createdAt = createdAt.toLocalDate();
    }
}
