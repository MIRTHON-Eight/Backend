package com.example.Bver.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseResponseStatus {
    /**
     * 성공 코드 2xx
     * 코드의 원활한 이해을 위해 code는 숫자가 아닌 아래 형태로 입력해주세요.
     */
    SUCCESS(true, HttpStatus.OK.value(), "요청에 성공하였습니다."),

    /**
     * Client Error - 4xx 에러
     */
    NO_AUTH(false, HttpStatus.UNAUTHORIZED.value(), "권한이 없습니다."),
    MEMBER_ALREADY_EXISTS(false, HttpStatus.CONFLICT.value(), "이미 존재하는 아이디입니다."),
    USERNAME_NOT_FOUND(false, HttpStatus.NOT_FOUND.value(), "존재하지 않은 아이디입니다."),
    INVALID_PASSWORD(false, HttpStatus.UNAUTHORIZED.value(), "잘못된 비밀번호입니다."),
    PASSWORD_MISMATCH(false, HttpStatus.BAD_REQUEST.value(), "입력하신 비밀번호가 다릅니다."),
    NON_EXIST_MEMBER(false, HttpStatus.NOT_FOUND.value(), "해당 사용자를 찾을 수 없습니다."),
    NON_EXIST_STORE(false, HttpStatus.NOT_FOUND.value(), "베이커리를 찾을 수 없습니다."),

    NOT_FOUND(false, HttpStatus.NOT_FOUND.value(), "Resource not found"),
    /**
     * Server Error - 5xx 에러
     */
    INTERNAL_SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "알 수 없는 에러가 발생하였습니다."),
    DATABASE_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "데이터베이스 에러가 발생했습니다."),
    ;

    private final boolean isSuccess;
    private final int code;
    private final String message;

    /**
     * isSuccess : 요청의 성공 또는 실패
     * code : Http Status Code
     * message : 설명
     */
    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
