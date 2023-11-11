package com.example.Bver.repository;

import com.example.Bver.entity.Member;
import com.example.Bver.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Override
    ArrayList<Reservation> findAll();

    List<Reservation> findAllByMemberId(Member member);
}
