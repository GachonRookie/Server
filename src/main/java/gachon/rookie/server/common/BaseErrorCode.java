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
