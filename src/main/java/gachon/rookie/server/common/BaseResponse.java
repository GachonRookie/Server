package gachon.rookie.server.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"success", "code", "message", "data"})
public class BaseResponse<T> {

    private boolean isSuccess;
    private Integer code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse(T data) {
        this.isSuccess = true;
        this.code = HttpStatus.OK.value();
        this.message = "요청에 성공하였습니다.";
        this.data = data;
    }

    public BaseResponse(BaseErrorDTO dto){
        this.isSuccess = false;
        this.code = dto.getCode();
        this.message = dto.getMessage();
    }
}
