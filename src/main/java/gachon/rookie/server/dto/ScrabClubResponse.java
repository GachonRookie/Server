package gachon.rookie.server.dto;

import gachon.rookie.server.entity.ClubTag;
import lombok.*;

import java.time.LocalDate;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ScrabClubResponse {

    // club
    private String logoUrl;
    private ClubTag clubTag;
    private String clubName;

    // clubrecruit
    private LocalDate recruitStartDate;
    private LocalDate recruitEndDate;

    // 1. ClubApply에서 gen을 추출한다.
    // 2. clubId가 같은 ClubApply를 전부 리스트에 담은 뒤 ClubApply 배열에서 gen이 같은 것들의 숫자를 모두 센다
    private int nowApplyTotalNumber;


    // 1. ClubApply에서 gen-1을 추출한다.
    // 만약 gen-1이 0이라면 저번 기수는 없는 것이므로.. 패스
    // 2. clubId가 같은 ClubApply를 전부 불러온 뒤 ClubApply 배열에서 gen이 같은 것들의 숫자를 모두 센다
    // 3. 그중 ApplyStatus가 PASS인 것의 개수를 센다
    private int lastPassNumber;
    private int lastApplyTotalNumber;

    private String applyPart;
}
