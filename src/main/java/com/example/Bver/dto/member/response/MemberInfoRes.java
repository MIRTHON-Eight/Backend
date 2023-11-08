package com.example.Bver.dto.member.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberInfoRes {

    private Long id;
    private String nickname;
    private String phoneNum;
    private String address;
    private String memberType;
}
