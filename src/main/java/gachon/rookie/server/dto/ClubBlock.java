package gachon.rookie.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubBlock {
    @Schema(name = "clubId", example = "1", description = "동아리 아이디(인덱스)")
    private Long clubId;
    @Schema(name = "clubName", example = "test", description = "동아리 이름")
    private String clubName;

    @Builder
    public ClubBlock(Long clubId, String clubName) {
        this.clubId = clubId;
        this.clubName = clubName;
    }
}
