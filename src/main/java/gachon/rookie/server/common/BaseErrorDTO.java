package gachon.rookie.server.common;

import lombok.Builder;
import lombok.Getter;

/**
 * 에러 처리시 사용하는 DTO
 * */
@Getter
public class BaseErrorDTO {

    private Integer code;
    private String message;

    @Builder
    public BaseErrorDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
