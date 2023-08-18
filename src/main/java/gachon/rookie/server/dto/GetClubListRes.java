package gachon.rookie.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetClubListRes {
    @Schema(name = "innerActiveList", example = "리스트", description = "고내 활성")
    private ClubCluster innerActiveList;
    @Schema(name = "innerNewList", example = "리스트", description = "고내 신규")
    private ClubCluster innerNewList;
    @Schema(name = "innerEndList", example = "리스트", description = "고내 폐지")
    private ClubCluster innerEndList;
    @Schema(name = "outerActiveList", example = "리스트", description = "연합 활성")
    private ClubCluster outerActiveList;
    @Schema(name = "outerNewList", example = "리스트", description = "연합 신규")
    private ClubCluster outerNewList;
    @Schema(name = "outerEndList", example = "리스트", description = "연합 폐지")
    private ClubCluster outerEndList;

    @Builder
    public GetClubListRes(ClubCluster innerActiveList, ClubCluster innerNewList, ClubCluster innerEndList, ClubCluster outerActiveList, ClubCluster outerNewList, ClubCluster outerEndList) {
        this.innerActiveList = innerActiveList;
        this.innerNewList = innerNewList;
        this.innerEndList = innerEndList;
        this.outerActiveList = outerActiveList;
        this.outerNewList = outerNewList;
        this.outerEndList = outerEndList;
    }
}
