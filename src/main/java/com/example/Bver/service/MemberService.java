package com.example.Bver.service;

import com.example.Bver.common.exception.BaseException;
import com.example.Bver.common.response.BaseResponseStatus;
import com.example.Bver.dto.member.response.MemberInfoRes;
import com.example.Bver.dto.member.response.MemberLoginRes;
import com.example.Bver.dto.member.response.MyPageRes;
import com.example.Bver.dto.store.res.StoreBriefRes;
import com.example.Bver.entity.Member;
import com.example.Bver.repository.MemberRepository;
import com.example.Bver.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final StoreService storeService;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String key;

    // 토큰의 유효 시간: 1시간 (60분)
    // 생성된 토큰은 발급된 후 1시간 동안 유효하며, 이 시간 이후에는 토큰이 만료된다.
    private Long expireTimeMs = 1000 * 60 * 60l;

    // 회원가입
    public String join(String userName, String password, String passwordCheck, String nickname, String phoneNum, String email, String address, String memberType) {

        // userName 중복 체크
        memberRepository.findByUserName(userName)
                .ifPresent(member -> {
                    throw new BaseException(BaseResponseStatus.MEMBER_ALREADY_EXISTS);
                });

        // 올바른 비밀번호를 입력했는지 확인
        if (!password.equals(passwordCheck)) {
            throw new BaseException(BaseResponseStatus.PASSWORD_MISMATCH);
        }

        Member member = Member.builder()
                .userName(userName)
                .password(encoder.encode(password))
                .passwordCheck(encoder.encode(password))
                .nickname(nickname)
                .phoneNum(phoneNum)
                .email(email)
                .address(address)
                .memberType(memberType)
                .build();

        memberRepository.save(member);

       return "success";
    }

    // 로그인
    public MemberLoginRes login(String userName, String password) {

        // userName이 없는 경우
        Member selectedMember = memberRepository.findByUserName(userName)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USERNAME_NOT_FOUND));

        // password가 틀린 경우
        if (!encoder.matches(password, selectedMember.getPassword())) {
            throw new BaseException(BaseResponseStatus.INVALID_PASSWORD);
        }

        // 오류가 발생하지 않는다면 토큰 발행
        String token = JwtTokenUtil.createToken(selectedMember.getMemberId(), selectedMember.getUserName(), key, expireTimeMs);

        return getMemberLoginRes(selectedMember, token);
    }

    // 회원 정보 조회 (닉네임, 전화번호, 주소)
    public MyPageRes getMemberInfo(Long memberId) {

        Member member1 = memberRepository.findById(memberId).orElseThrow(() -> new BaseException(BaseResponseStatus.NON_EXIST_MEMBER));
        MemberInfoRes memberInfoResDto = getMemberInfoResDto(member1);
        List<StoreBriefRes> myBakeries = storeService.getMyBakeries(memberId);
        return new MyPageRes(memberInfoResDto, myBakeries);
    }

    // Dto 변환
    public MemberInfoRes getMemberInfoResDto(Member member) {

        MemberInfoRes memberInfoResDto = MemberInfoRes.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .phoneNum(member.getPhoneNum())
                .address(member.getAddress())
                .memberType(member.getMemberType())
                .build();

        return memberInfoResDto;
    }

    public MemberLoginRes getMemberLoginRes(Member member, String token) {

        MemberLoginRes memberLoginRes  = MemberLoginRes.builder()
                .memberId(member.getMemberId())
                .token(token)
                .build();

        return memberLoginRes;
    }
}
