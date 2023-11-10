package com.example.Bver.dto.member.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberJoinReq {

    private String nickname;
    private String userName;
    private String password;
    private String passwordCheck;
    private String phoneNum;
    private String email;
    private String address;
    private String memberType;
  
}
