package com.wanted.wanted_pre_onboarding_backend.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StatusCode {

    SUCCESS(HttpStatus.OK, "요청에 성공하였습니다."),
    SUCCESS_CREATED(HttpStatus.CREATED, "생성되었습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "요청 값을 확인해주세요."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 통신 중 오류가 발생하였습니다.."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    StatusCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
