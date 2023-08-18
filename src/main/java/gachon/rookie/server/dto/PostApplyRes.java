package gachon.rookie.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostApplyRes {

    @Schema(name = "applyResult", example = "등록에 성공하였습니다.")
    private String applyResult;

    public PostApplyRes(String applyResult) {
        this.applyResult = applyResult;
    }
}
