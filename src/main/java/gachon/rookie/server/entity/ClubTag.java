package gachon.rookie.server.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClubTag {

    INTERNAL("교내 동아리"),
    UNION("연합 동아리");
    private String statusKo;
}
