package gachon.rookie.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMainClubDetailRes {

    @Schema(name = "clubName", description = "동아리 이름", example = "UMC")
    private String clubName;
    @Schema(name = "activityStartDate", description = "활동 시작 일자", example = "2023-08-19")
    private String activityStartDate;
    @Schema(name = "activityEndDate", description = "활동 종료 일자", example = "2023-08-19")
    private String activityEndDate;
    @Schema(name = "applyStartDate", description = "모집 시작 일자", example = "2023-08-19")
    private String applyStartDate;
    @Schema(name = "applyEndDate", description = "모집 종료 일자", example = "2023-08-19")
    private String applyEndDate;
    @Schema(name = "target", description = "모집 대상", example = "가천대학교 재학생 및 휴학생")
    private String target;
    @Schema(name = "parts", description = "모집 파트 리스트", example = "Server, Web, iOS")
    private List<String> parts;

    @Builder
    public GetMainClubDetailRes(String clubName, String activityStartDate, String activityEndDate, String applyStartDate, String applyEndDate, String target, List<String> parts) {
        this.clubName = clubName;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
        this.applyStartDate = applyStartDate;
        this.applyEndDate = applyEndDate;
        this.target = target;
        this.parts = parts;
    }
}
