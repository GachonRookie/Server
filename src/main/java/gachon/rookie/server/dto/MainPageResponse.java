package gachon.rookie.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MainPageResponse {
    private String nickname;
    private NewClubResponse newClubResponse;
    private List<NotNewClubResponse> notNewClubResponseList;
}
