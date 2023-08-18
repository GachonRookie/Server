package gachon.rookie.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostApplyReq {

    @Schema(name = "clubId", description = "동아리 아이디(인덱스)", example = "1")
    private Long clubId;
    @Schema(name = "part", description = "지원 파트", example = "Server")
    private String part;
}
