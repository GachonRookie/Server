package gachon.rookie.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Report {
    @Schema(name = "authorName", example = "name", description = "작성자 이름")
    private String authorName;
    @Schema(name = "content", example = "내용", description = "내용")
    private String content;
    @Schema(name = "createdAt", example = "작성일시", description = "time")
    private String createdAt;

    @Builder
    public Report(String authorName, String content, String createdAt) {
        this.authorName = authorName;
        this.content = content;
        this.createdAt = createdAt;
    }
}
