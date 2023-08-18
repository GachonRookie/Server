package gachon.rookie.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClubSubType {
    IT("IT"),
    CONV("융합"),
    ECO("경제"),
    ETC("기타");

    private String statusKo;
}
