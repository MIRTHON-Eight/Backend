package com.example.Bver.controller;

import com.example.Bver.common.exception.BaseException;
import com.example.Bver.common.response.BaseResponse;
import com.example.Bver.dto.member.request.MemberJoinReq;
import com.example.Bver.dto.member.request.MemberLoginReq;
import com.example.Bver.dto.member.response.MemberInfoRes;
import com.example.Bver.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/join")
    public BaseResponse<String> join(@RequestBody MemberJoinReq memberJoinReq){
        try {
            memberService.join(memberJoinReq.getUserName(), memberJoinReq.getPassword(), memberJoinReq.getPasswordCheck(), memberJoinReq.getNickname(), memberJoinReq.getEmail(),
                    memberJoinReq.getPhoneNum(), memberJoinReq.getAddress(), memberJoinReq.getUserType());
            return new BaseResponse<String>("회원가입에 성공하였습니다.");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    // 로그인
    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody MemberLoginReq memberLoginReq) {
        try {
            String token = memberService.login(memberLoginReq.getUserName(), memberLoginReq.getPassword());
            return new BaseResponse<>(token);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/members/{memberId}")
    public BaseResponse<MemberInfoRes> memberInfo(@PathVariable("memberId") Long id) {
        try {
            MemberInfoRes memberInfo = memberService.getMemberInfo(id);
            return new BaseResponse<>(memberInfo);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
