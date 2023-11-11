package com.example.Bver.dto.reservation;

import com.example.Bver.entity.Bread;
import com.example.Bver.entity.Reservation;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservationForm {
    private Long id;
    private Integer quantity; //빵 수량
//    private Date pickUpDate; // 수령 날짜
//    private String pickUpTime; // 수령 시간

//    public Reservation toEntity() {
//        return new Reservation(id, quantity);
//    }
//    public Reservation toEntity() {
//        Reservation reservation = new Reservation();
//        reservation.setQuantity(quantity);
//        // Bread 엔티티는 여기서 설정하지 않고, 호출하는 측에서 필요에 따라 설정해야 합니다.
//        return reservation;
//    }

}
