package com.example.Bver.repository;

import com.example.Bver.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query(value = "SELECT * FROM STORE order by RAND() limit 5",nativeQuery = true)
    List<Store> findAllRandom();
}
