package gachon.rookie.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReportReq {
    @Schema(name = "clubId", example = "1", description = "작성대상 동아리 인덱스")
    private Long clubId;
    @Schema(name = "content", example = "내용", description = "레포트 내용")
    private String content;
}
