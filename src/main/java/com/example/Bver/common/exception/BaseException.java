package com.example.Bver.common.exception;

import com.example.Bver.common.response.BaseResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private BaseResponseStatus status;

    /**
     * throw new BaseException(BaseResponseStatus.DATABASE_INSERT_ERROR)와 같이 사용.
     */
    public BaseException(BaseResponseStatus status) {
        super(status.getMessage());
        this.status = status;
    }
}
