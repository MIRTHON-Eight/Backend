package com.example.Bver.controller;

import com.example.Bver.common.exception.BaseException;
import com.example.Bver.common.response.BaseResponse;
import com.example.Bver.common.response.BaseResponseStatus;
import com.example.Bver.dto.reservation.ReservationForm;
import com.example.Bver.entity.Bread;
import com.example.Bver.entity.Reservation;
import com.example.Bver.entity.ReservationBread;
import com.example.Bver.repository.BreadRepository;
import com.example.Bver.repository.ReservationBreadRepository;
import com.example.Bver.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    private BreadRepository breadRepository;

    private ReservationBreadRepository reservationBreadRepository;

    @GetMapping("/api/reservations")
    public BaseResponse<List<Map<String, Integer>>> getReservations() {
        try {
            List<Reservation> reservations = reservationRepository.findAll();

            List<Map<String, Integer>> responseList = reservations.stream()
                    .map(reservation -> Collections.singletonMap("id",reservation.getId().intValue()))
                    .collect(Collectors.toList());

            //성공응답
            return new BaseResponse<>(responseList);
        } catch (Exception e) {
            // 예외가 발생했을 때의 응답 처리
            return new BaseResponse<>(false, BaseResponseStatus.INTERNAL_SERVER_ERROR.getCode(), "조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @GetMapping("/api/reservations/{id}")
    public BaseResponse<?> show(@PathVariable Long id) {
        return reservationRepository.findById(id)
                .map(reservation -> new BaseResponse<>(
                        true, // isSuccess
                        "예약 조회 성공", // message
                        BaseResponseStatus.SUCCESS.getCode(), // code
                        reservation // result
                ))
                .orElse(new BaseResponse<>(
                        false, // isSuccess
                        "해당 ID로 예약을 찾을 수 없습니다.",// message
                        BaseResponseStatus.NOT_FOUND.getCode(), // code
                        null // result
                ));
    }

    @PostMapping("/api/reservations")
    public BaseResponse<Integer> create(@RequestBody List<ReservationForm> dto) {
        try {
            // DTO를 엔티티로 변환

//            Reservation reservation = dto.toEntity();
            Reservation reservation = new Reservation();
            // 리파지터리로 엔티티를 DB에 저장
            Reservation saved = reservationRepository.save(reservation);

            for (ReservationForm form : dto){
                Bread bread = breadRepository.findById(form.getId()).orElseThrow(()-> new BaseException(BaseResponseStatus.NON_EXIST_STORE));
                ReservationBread rb = ReservationBread.toEntity(bread,saved,form.getQuantity());

                reservationBreadRepository.save(rb);
            }
            // 성공 응답 반환
            return new BaseResponse<>(true,
                    "예약이 성공적으로 생성되었습니다.",
                    BaseResponseStatus.SUCCESS.getCode(),
                    saved.getId().intValue());
        } catch (Exception e) {
            // 실패 응답 반환
            return new BaseResponse<>(false, BaseResponseStatus.DATABASE_ERROR.getCode(), "예약 생성에 실패했습니다: " + e.getMessage());
        }
    }


}
