package com.example.Bver.repository;

import com.example.Bver.entity.Bread;
import com.example.Bver.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreadRepository extends JpaRepository<Bread, Long> {
    List<Bread> findAllByStoreId(Store storeId);
}
