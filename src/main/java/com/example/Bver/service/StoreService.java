package com.example.Bver.service;

import com.example.Bver.common.exception.BaseException;
import com.example.Bver.common.response.BaseResponseStatus;
import com.example.Bver.dto.store.res.StoreDetailRes;
import com.example.Bver.dto.store.res.StoreHomeRes;
import com.example.Bver.entity.Member;
import com.example.Bver.entity.MyBakery;
import com.example.Bver.entity.Store;
import com.example.Bver.repository.BreadRepository;
import com.example.Bver.repository.MemberRepository;
import com.example.Bver.repository.MyBakeryRepository;
import com.example.Bver.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final BreadRepository breadRepository;
    private final MyBakeryRepository myBakeryRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<StoreHomeRes> getBakeries() {
        return storeRepository.findAll()
                .stream().map(StoreHomeRes::toStoreHomeRes)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StoreDetailRes getBakery(Long storeId) throws BaseException{
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new BaseException(BaseResponseStatus.NON_EXIST_STORE));
        List<StoreDetailRes.MenuBriefRes> menuBriefResList = breadRepository
                .findAllByStoreId(store)
                .stream()
                .map(StoreDetailRes.MenuBriefRes::toMenuBriefRes)
                .collect(Collectors.toList());

        return StoreDetailRes.builder()
                .storeImg(store.getStorePhoto())
                .storeLogo(store.getStoreLogo())
                .storeName(store.getStoreName())
                .openTime(store.getOperatingHour())
                .location(store.getLocation())
                .isLike(false) // todo : 회원 찜 구현 시 isLike 조회.
                .menuList(menuBriefResList)
                .build();
    }

    public List<StoreHomeRes> getMyBakeries(Long memberId) throws BaseException {
        try {
            // MyBakeryRepository를 통해 memberId에 해당하는 MyBakery 목록을 조회
            Member member = memberRepository.findById(memberId).orElseThrow(()-> new BaseException(BaseResponseStatus.NON_EXIST_MEMBER));
            List<MyBakery> myBakeries = myBakeryRepository.findAllByMember(member);

            System.out.println("myBakeries = " + myBakeries);
            
            // MyBakery 목록을 StoreHomeRes DTO로 변환
            return myBakeries.stream()
                    .map(myBakery -> {
                        Store store = myBakery.getStore();
                        return StoreHomeRes.toStoreHomeRes(store);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // 예외 발생 시 BaseException을 통해 에러 처리
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

}
