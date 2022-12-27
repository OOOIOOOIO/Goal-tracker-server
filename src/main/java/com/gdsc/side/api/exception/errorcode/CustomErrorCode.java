package com.gdsc.side.api.exception.errorcode;

import com.gdsc.side.api.exception.type.TokenRefreshException;
import lombok.Getter;


public enum CustomErrorCode {
    // common
    MethodArgumentNotValidException("U001", "유효하지 않은 형식입니다."),
    IllegalArgumentException("U002", "적절하지 못한 인자입니다."),
    UserNotFoundException("U003", "유저가 존재하지 않습니다."),
    DailyNotFoundException("U004", "데일리 목표가 존재하지 않습니다."),
    DailyDatesNotFoundException("U005", "해당 날짜에 데일리 목표가 존재하지 않습니다."),
    GoalNotFoundException("U006", "장기 목표가 존재하지 않습니다."),
    DiaryNotFoundException("U007", "일기가 존재하지 않습니다."),
    NoHandlerFoundException("U008", "잘못된 uri 요청입니다."),
    METHOD_NOT_ALLOWED("U009", "잘못된 메서드 요청입니다."),
    INTERNAL_SERVER_ERROR("U010", "서버 에러! 다시 시도해주세요");




    private String code;
    private String message;


    CustomErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public String message(){
        return message;
    }

    public String code(){
        return code;
    }

}
