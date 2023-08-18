package gachon.rookie.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetRegisterDetailRes {
    @Schema(name = "clubName", example = "동아리 이름", description = "동아리 이름")
    private String clubName;
    @Schema(name = "applyLink", example = "url", description = "지원 링크")
    private String applyLink;
    @Schema(name = "activityStart", example = "time", description = "활동 시작 일시")
    private String activityStart;
    @Schema(name = "activityEnd", example = "time", description = "활동 종료 일시")
    private String activityEnd;
    @Schema(name = "target", example = "가천대학교 재학생 및 휴학생", description = "모집 대상")
    private String target;
    @Schema(name = "parts", example = "part List", description = "모집 파트 리스트")
    private List<String> parts;
    @Schema(name = "reports", example = "Report List", description = "리포트 리스트")
    private List<Report> reports;

    @Builder
    public GetRegisterDetailRes(String clubName, String applyLink, String activityStart, String activityEnd, String target, List<String> parts, List<Report> reports) {
        this.clubName = clubName;
        this.applyLink = applyLink;
        this.activityStart = activityStart;
        this.activityEnd = activityEnd;
        this.target = target;
        this.parts = parts;
        this.reports = reports;
    }
}
