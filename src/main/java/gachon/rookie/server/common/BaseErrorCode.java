package gachon.rookie.server.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 서버 내부 처리시 사용하는 에러 리스트
 * */
@Getter
public enum BaseErrorCode {
    //400
    JWT_NOT_EXIST(HttpStatus.BAD_REQUEST, "JWT 존재하지 않음"),
    JWT_INVALID(HttpStatus.BAD_REQUEST, "유효한 JWT가 아닙니다."),

    //404
    CLUB_NOT_EXIST(HttpStatus.NOT_FOUND, "대상 동아리가 존재하지 않습니다."),
    RECRUIT_NOT_EXIST(HttpStatus.NOT_FOUND, "동아리 모집이 존재하지 않습니다."),
    MEMBER_NOT_EXIST(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),
    REPORT_NOT_EXIST(HttpStatus.NOT_FOUND, "리포트가 존재하지 않습니다."),
    APPLY_NOT_EXIST(HttpStatus.NOT_FOUND, "모집이 존재하지 않습니다."),

    //409
    APPLY_EXIST(HttpStatus.CONFLICT, "이미 지원한 동아리 입니다."),
    CLUB_EXIST(HttpStatus.CONFLICT, "이미 동아리가 존재합니다."),
    //500
    JWT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "JWT ERROR"),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Database Error"),
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected Error");


    private final HttpStatus code;
    private final String message;

    BaseErrorCode(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
}
