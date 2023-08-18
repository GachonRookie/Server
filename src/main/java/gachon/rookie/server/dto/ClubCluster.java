package gachon.rookie.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubCluster {

    @Schema(name = "typeTag", example = "INTERNAL", description = "INTERNAL: 고내, UNION: 연합")
    private String typeTag;
    @Schema(name = "statusTag", example = "ACTIVE", description = "ACTIVE: 활동, INACTIVE: 신규, DELETE: 폐지")
    private String statusTag;
    @Schema(name = "clubs", example = "동아리 리스트", description = "동아리 리스트")
    List<ClubBlock> clubs;

    @Builder
    public ClubCluster(String typeTag, String statusTag, List<ClubBlock> clubs) {
        this.typeTag = typeTag;
        this.statusTag = statusTag;
        this.clubs = clubs;
    }
}
