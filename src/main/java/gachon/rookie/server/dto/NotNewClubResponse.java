package gachon.rookie.server.dto;

import gachon.rookie.server.entity.ClubPart;
import gachon.rookie.server.entity.RecruitStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class NotNewClubResponse {
    private Long recruitId;
    private String clubName;
    private Integer gen;
    private String logourl;
    private RecruitStatus recruitStatus;
    private List<ClubPart> parts;
}
