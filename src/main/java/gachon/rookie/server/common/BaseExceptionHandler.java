package gachon.rookie.server.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 서버 내부 예외 처리 클래스
 * */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponse<BaseErrorDTO> baseExceptionHandler(BaseException exception) {
        log.warn("BaseException raised : {}", exception.getCode().getMessage());

        return new BaseResponse<>(
                BaseErrorDTO.builder()
                        .code(exception.getCode().getCode().value())
                        .message(exception.getCode().getMessage())
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<BaseErrorDTO> unexpectedExceptionHandler(Exception exception) {
        log.error("BaseException raised : {}", exception.getMessage());
        exception.printStackTrace();

        return new BaseResponse<>(
                BaseErrorDTO.builder()
                        .code(BaseErrorCode.UNEXPECTED_ERROR.getCode().value())
                        .message(BaseErrorCode.UNEXPECTED_ERROR.getMessage())
                        .build()
        );
    }
}
