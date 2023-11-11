package com.example.Bver.controller;

import com.example.Bver.common.exception.BaseException;
import com.example.Bver.common.response.BaseResponse;
import com.example.Bver.dto.store.res.StoreDetailRes;
import com.example.Bver.dto.store.res.StoreHomeRes;
import com.example.Bver.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/api/bakery")
    public BaseResponse<List<StoreHomeRes>> getBakeries() {
        try {
            List<StoreHomeRes> bakeries = storeService.getBakeries();
            return new BaseResponse<>(bakeries);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/api/bakery/{storeId}")
    public BaseResponse<StoreDetailRes> getBakery(@PathVariable Long storeId) {
        try {
            StoreDetailRes bakery = storeService.getBakery(storeId);
            return new BaseResponse<>(bakery);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        } catch (Exception e) {
            return new BaseResponse<>(false, 404, "로그인에 실패하였습니다");
        }
    }

    @GetMapping("/api/myBakery/{memberId}")
    public BaseResponse<List<StoreHomeRes>> getMyBakeries(@PathVariable Long memberId) {
        try {
            List<StoreHomeRes> myBakeries = storeService.getMyBakeries(memberId);
            return new BaseResponse<>(myBakeries);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        } catch (Exception e) {
            return new BaseResponse<>(false, 500, "서버 내부 오류가 발생했습니다");
        }
    }
}
