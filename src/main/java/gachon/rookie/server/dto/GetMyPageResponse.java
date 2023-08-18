package gachon.rookie.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class GetMyPageResponse {
    private String nickname;
    private List<ScrabClubResponse> scrabClubResponseList;
}
