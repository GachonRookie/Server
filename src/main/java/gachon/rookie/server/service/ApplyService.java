package gachon.rookie.server.service;

import gachon.rookie.server.common.BaseException;
import gachon.rookie.server.dto.GetMyPageResponse;
import gachon.rookie.server.dto.ScrabClubResponse;
import gachon.rookie.server.entity.*;
import gachon.rookie.server.repository.ClubApplyRepository;
import gachon.rookie.server.repository.ClubRecruitRepository;
import gachon.rookie.server.repository.ClubRepository;
import gachon.rookie.server.repository.MemberRepository;
import gachon.rookie.server.utils.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplyService {

    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final ClubRecruitRepository clubRecruitRepository;
    private final ClubApplyRepository clubApplyRepository;
    private final JwtUtil jwtUtil;
    private Member member = null;

    public ApplyService(ClubRepository clubRepository, MemberRepository memberRepository, ClubRecruitRepository clubRecruitRepository, ClubApplyRepository clubApplyRepository, JwtUtil jwtUtil) {
        this.clubRepository = clubRepository;
        this.memberRepository = memberRepository;
        this.clubRecruitRepository = clubRecruitRepository;
        this.clubApplyRepository = clubApplyRepository;
        this.jwtUtil = jwtUtil;
    }
    @Transactional
    public GetMyPageResponse getMyPage(String jwt) throws BaseException {

        String userToken = String.valueOf(jwtUtil.getUserIdx(jwtUtil.getJwt()));

        member = memberRepository.findByUserToken(userToken).orElse(null);

        List<ClubApply> clubApplies = null;

        if (member != null) {
            clubApplies = member.getClubApplies();
            if (clubApplies.isEmpty()) {
                return null;
            }
        }

        List<ScrabClubResponse> responses = new ArrayList<>();
        for (ClubApply apply : clubApplies) {
            ScrabClubResponse response = new ScrabClubResponse();

            // 1. Get clubId from apply
            Long clubId = apply.getClubId().getClubId();
            Club club = clubRepository.findClubByClubId(clubId);

            // 3. Assign Club data to ScrabClubResponse
            response.setLogoUrl(club.getLogoUrl());
            response.setClubTag(club.getClubTag());
            response.setClubName(club.getClubName());

            // 4. Get gen and clubId from apply
            Integer gen = apply.getGen();

            // 5. Find ClubRecruit by clubId and gen (assuming a method exists to do this)
            ClubRecruit clubRecruit = clubRecruitRepository.findByClubIdAndGen(clubId, gen);

            // 6. Assign recruitStartDate and recruitEndDate
            response.setRecruitStartDate(clubRecruit.getRecruitStartDate());
            response.setRecruitEndDate(clubRecruit.getRecruitEndDate());

            // 7. Extract gen from apply and 8. Count columns with same clubId and gen
            int nowApplyTotalNumber = clubApplyRepository.countByClubIdAndGen(clubId, gen);

            // 9. Assign nowApplyTotalNumber
            response.setNowApplyTotalNumber(nowApplyTotalNumber);

            // 10. Initialize lastPassNumber and lastApplyTotalNumber if gen is 1
            if (gen == 1) {
                response.setLastPassNumber(0);
                response.setLastApplyTotalNumber(0);
            } else {
                // 12. Count columns with clubId and gen-1
                int lastApplyTotalNumber = clubApplyRepository.countByClubIdAndGen(clubId, gen - 1);

                // 13. Assign lastApplyTotalNumber
                response.setLastApplyTotalNumber(lastApplyTotalNumber);

                // 14. Count columns with clubId and gen-1 where applyStatus is PASS
                int lastPassNumber = clubApplyRepository.countByClubIdAndGenAndApplyStatus(clubId, gen - 1, ApplyStatus.PASS);

                // 15. Assign lastPassNumber
                response.setLastPassNumber(lastPassNumber);
            }

            responses.add(response);
        }

        return GetMyPageResponse.builder()
                .nickname(member.getNickname())
                .scrabClubResponseList(responses)
                .build();



    }
}

