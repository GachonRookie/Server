package gachon.rookie.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostClubReq {
    @Schema(name = "clubName", example = "UMC", description = "동아리 이름")
    private String clubName;
    @Schema(name = "clubType", example = "1", description = "0이면 교내 1이면 연합")
    private Integer clubType;
    @Schema(name = "partList", example = "모집 파트 리스트", description = "파트 리스트")
    private List<String> partList;
    @Schema(name = "applyLink", example = "모집 링크", description = "모집 링크")
    private String applyLink;
    @Schema(name = "content", example = "홍보글 내용", description = "홍보글 내용")
    private String content;
}
