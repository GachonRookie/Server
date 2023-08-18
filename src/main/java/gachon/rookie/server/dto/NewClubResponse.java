package gachon.rookie.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewClubResponse {
    private Long clubId;
    private String clubName;
    private String logourl;
}
