package gachon.rookie.server.common;


import lombok.Getter;

/**
 * 서버 내부 예외 클래스
 * */
@Getter
public class BaseException extends Exception{
    private BaseErrorCode code;

    public BaseException(BaseErrorCode code) {
        this.code = code;
    }
}
