package com.example.Bver.dto.member.response;

import com.example.Bver.dto.store.res.StoreBriefRes;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class MyPageRes {
    private MemberInfoRes memberInfoRes;
    private List<StoreBriefRes> myBakeryList;

    public MyPageRes(MemberInfoRes memberInfoRes, List<StoreBriefRes> myBakeryList) {
        this.memberInfoRes = memberInfoRes;
        this.myBakeryList = myBakeryList;
    }
}
