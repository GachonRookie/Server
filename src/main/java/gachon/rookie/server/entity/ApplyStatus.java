package gachon.rookie.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplyStatus {
    PASS("합격"),
    FAIL("불합격"),
    STANDBY("지원중");

    private String statusKo;
}
