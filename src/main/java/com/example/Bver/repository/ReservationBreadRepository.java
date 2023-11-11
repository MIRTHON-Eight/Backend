package com.example.Bver.repository;

import com.example.Bver.entity.Reservation;
import com.example.Bver.entity.ReservationBread;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationBreadRepository extends JpaRepository<ReservationBread, Long> {

    List<ReservationBread> findAllByReservationId(Reservation reservation);

}
