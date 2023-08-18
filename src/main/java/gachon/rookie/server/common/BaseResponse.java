package gachon.rookie.server.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"success", "code", "message", "data"})
public class BaseResponse<T> {

    private boolean isSuccess;
    private Integer code;
    @Schema(name = "message", example = "바로 위의 description에 있습니다. | 로 구분 된것은 서로 다른 메세지 입니다.")
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
