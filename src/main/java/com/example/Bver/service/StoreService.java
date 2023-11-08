package com.example.Bver.service;

import com.example.Bver.common.exception.BaseException;
import com.example.Bver.common.response.BaseResponseStatus;
import com.example.Bver.dto.store.res.StoreDetailRes;
import com.example.Bver.dto.store.res.StoreHomeRes;
import com.example.Bver.entity.Store;
import com.example.Bver.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public List<StoreHomeRes> getBakeries() {
        return storeRepository.findAll()
                .stream().map(StoreHomeRes::toStoreHomeRes)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StoreDetailRes getBakery(Long storeId) throws BaseException{
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new BaseException(BaseResponseStatus.NON_EXIST_STORE));

        return StoreDetailRes.builder()
                .storeImg(store.getStorePhoto())
                .storeLogo(store.getStoreLogo())
                .storeName(store.getStoreName())
                .longitude(store.getLongitude())
                .latitude(store.getLatitude())
                .isLike(false) // todo : 회원 찜 구현 시 isLike 조회.
                .menuList(List.of()) // todo : 해당 가게 메뉴 조회 후 삽입.
                .build();
    }
}
