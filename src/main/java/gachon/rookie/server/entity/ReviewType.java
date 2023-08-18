package gachon.rookie.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewType {

    APPLY("지원후기"),
    ACTIVITY("활동후기");

    private String statusKo;
}
