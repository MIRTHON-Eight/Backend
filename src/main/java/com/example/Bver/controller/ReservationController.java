package com.example.Bver.controller;

import com.example.Bver.common.exception.BaseException;
import com.example.Bver.common.response.BaseResponse;
import com.example.Bver.common.response.BaseResponseStatus;
import com.example.Bver.dto.reservation.ReservationBreadBriefRes;
import com.example.Bver.dto.reservation.ReservationBriefRes;
import com.example.Bver.dto.reservation.ReservationForm;
import com.example.Bver.entity.*;
import com.example.Bver.repository.BreadRepository;
import com.example.Bver.repository.MemberRepository;
import com.example.Bver.repository.ReservationBreadRepository;
import com.example.Bver.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final BreadRepository breadRepository;

    private final ReservationBreadRepository reservationBreadRepository;

    @GetMapping("/api/reservations/list/{memberId}")
    public BaseResponse<List<ReservationBriefRes>> getReservations(@PathVariable Long memberId) {
        try {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new BaseException(BaseResponseStatus.NON_EXIST_MEMBER));
            List<ReservationBriefRes> reservationBriefRes = reservationRepository.findAllByMemberId(member).stream().map(
                    reservation ->
                    {
                        ReservationBriefRes res = ReservationBriefRes.toReservationBriefRes(reservation);
                        res.setStoreName(reservation.getStoreId().getStoreName());
                        List<ReservationBread> reservationBreadList = reservationBreadRepository.findAllByReservationId(reservation);
                        List<ReservationBreadBriefRes> reservationBreadBriefRes = reservationBreadList.stream().map(ReservationBreadBriefRes::toReservationBriefRes).collect(Collectors.toList());
                        res.setBreadsInfo(reservationBreadBriefRes);
                        return res;
                    }
            ).collect(Collectors.toList());
            //성공응답
            return new BaseResponse<>(reservationBriefRes);
        } catch (Exception e) {
            // 예외가 발생했을 때의 응답 처리
            return new BaseResponse<>(false, BaseResponseStatus.INTERNAL_SERVER_ERROR.getCode(), "조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @GetMapping("/api/reservations/{reservationId}")
    public BaseResponse<List<ReservationBreadBriefRes>> show(@PathVariable Long reservationId) {
        try {
            Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND));
            List<ReservationBread> reservationBreadList = reservationBreadRepository.findAllByReservationId(reservation);
            List<ReservationBreadBriefRes> reservationBreadBriefRes = reservationBreadList.stream().map(ReservationBreadBriefRes::toReservationBriefRes).collect(Collectors.toList());
            return new BaseResponse<>(reservationBreadBriefRes);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping("/api/reservations/{memberId}")
    public BaseResponse<Integer> create(@PathVariable Long memberId, @RequestBody List<ReservationForm> dto) {
        try {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new BaseException(BaseResponseStatus.NON_EXIST_MEMBER));
            Store store = breadRepository.findById(dto.get(0).getId()).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND)).getStoreId();
            // DTO를 엔티티로 변환
            Reservation reservation = new Reservation(member, store);
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
