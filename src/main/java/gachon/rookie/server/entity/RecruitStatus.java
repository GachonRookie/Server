package gachon.rookie.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecruitStatus {
    SCHEDULED("모집 예정"),
    RECRUITING("모집중"),
    DEADLINE("모집 마감");

    private String statusKo;
}
