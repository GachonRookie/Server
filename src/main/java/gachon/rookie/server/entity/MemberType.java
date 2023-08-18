package gachon.rookie.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {
    USER("유저"),
    ADMIN("관리자");

    private String statusKo;
}
