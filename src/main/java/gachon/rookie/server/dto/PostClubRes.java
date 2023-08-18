package gachon.rookie.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostClubRes {
    @Schema(name = "addClubSuccess", example = "동아리 추가에 성공하였습니다.", description = "성공 여부")
    private String addClubSuccess;
}
