package com.example.Bver.repository;

import com.example.Bver.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Override
    ArrayList<Reservation> findAll();

}
