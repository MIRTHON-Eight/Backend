package com.example.Bver.controller;

import com.example.Bver.common.exception.BaseException;
import com.example.Bver.common.response.BaseResponse;
import com.example.Bver.dto.store.res.StoreDetailRes;
import com.example.Bver.dto.store.res.StoreBriefRes;
import com.example.Bver.dto.store.res.StoreHomeRes;
import com.example.Bver.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/api/bakery")
    public BaseResponse<StoreHomeRes> getBakeries() {
        try {
            StoreHomeRes storeHomeRes = storeService.getBakeries();
            return new BaseResponse<>(storeHomeRes);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/api/bakery/{memberId}/{storeId}")
    public BaseResponse<StoreDetailRes> getBakery(@PathVariable Long memberId, @PathVariable Long storeId) {
        try {
            StoreDetailRes bakery = storeService.getBakery(memberId, storeId);
            return new BaseResponse<>(bakery);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/api/myBakery/{memberId}")
    public BaseResponse<List<StoreBriefRes>> getMyBakeries(@PathVariable Long memberId) {
        try {
            List<StoreBriefRes> myBakeries = storeService.getMyBakeries(memberId);
            return new BaseResponse<>(myBakeries);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        } catch (Exception e) {
            return new BaseResponse<>(false, 500, "서버 내부 오류가 발생했습니다");
        }
    }

    @PostMapping("/api/bakery/like/{memberId}/{storeId}")
    public BaseResponse<String> likeBakery(@PathVariable Long memberId, @PathVariable Long storeId) {
        try {
            storeService.likeMyBakery(memberId, storeId);
            return new BaseResponse<>("해당 가게를 찜하였습니다.");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

}
