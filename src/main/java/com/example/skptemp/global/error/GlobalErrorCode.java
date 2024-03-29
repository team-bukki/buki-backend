package com.example.skptemp.global.error;

import lombok.Getter;

@Getter
public enum GlobalErrorCode {


    SUCCESS(200, "G000", "요청에 성공했습니다."),
    OTHER(500, "G100", "서버에 오류가 발생했습니다"),
    METHOD_NOT_ALLOWED(405, "G200", "허용되지 않은 메서드입니다"),

    VALID_EXCEPTION(400, "G300", "유효 하지 않은 요청입니다."),

    USER_VALID_EXCEPTION(400, "G310", "유저가 유효하지 않습니다."),

    ITEM_VALID_EXCEPTION(400, "G320", "아이템이 유효하지 않습니다."),
    ITEM_COUNT_EXCEPTION(400, "G321", "아이템 개수가 유효하지 않습니다."),

    CHARM_VALID_EXCEPTION(400, "G330", "부적이 유효하지 않습니다."),

    FRIEND_RELATIONSHIP_VALID_EXCEPTION(400, "G340", "친구 관계가 유효하지 않습니다."),

    ACCESS_DENIED(401, "G400", "허용되지 않은 사용자입니다"),

    TOKEN_EXPIRED(401, "G500", "토큰이 만료되었습니다."),

    USER_CONFLICT(409, "G600", "이미 가입된 내역이 있습니다."),


    ;
    private final String code;
    private final String message;
    private final int status;

    GlobalErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
