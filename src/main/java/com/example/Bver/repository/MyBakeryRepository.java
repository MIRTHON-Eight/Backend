package com.example.Bver.repository;

import com.example.Bver.entity.Member;
import com.example.Bver.entity.MyBakery;
import com.example.Bver.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MyBakeryRepository extends CrudRepository<MyBakery, Long> {

    Boolean existsByMemberAndStore(Member member, Store store);
    // memberId를 기준으로 MyBakery 리스트를 조회하는 메소드
    List<MyBakery> findAllByMember(Member memberId);
}
