package gachon.rookie.server.service;

import gachon.rookie.server.common.BaseException;
import gachon.rookie.server.dto.JwtResponse;
import gachon.rookie.server.dto.MainPageResponse;
import gachon.rookie.server.dto.NewClubResponse;
import gachon.rookie.server.dto.NotNewClubResponse;
import gachon.rookie.server.entity.Club;
import gachon.rookie.server.entity.ClubRecruit;
import gachon.rookie.server.entity.Member;
import gachon.rookie.server.entity.RecruitStatus;
import gachon.rookie.server.repository.ClubApplyRepository;
import gachon.rookie.server.repository.ClubRecruitRepository;
import gachon.rookie.server.repository.ClubRepository;
import gachon.rookie.server.repository.MemberRepository;
import gachon.rookie.server.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final ClubRecruitRepository clubRecruitRepository;
    private final ClubApplyRepository clubApplyRepository;
    private final JwtUtil jwtUtil;
    private Member member = null;

    @Autowired
    public MemberService(ClubRepository clubRepository, MemberRepository memberRepository, ClubRecruitRepository clubRecruitRepository, ClubApplyRepository clubApplyRepository, JwtUtil jwtUtil) {
        this.clubRepository = clubRepository;
        this.memberRepository = memberRepository;
        this.clubRecruitRepository = clubRecruitRepository;
        this.clubApplyRepository = clubApplyRepository;
        this.jwtUtil = jwtUtil;
    }

    private void setMember(String userToken, String nickName){

        member = memberRepository.findByUserToken(userToken).orElse(null);

        // 유저가 존재하지 않으면 save로 업데이트
        // 유저가 존재하면, 더 굳이 업데이트 할 이유가 없다.
        if(member == null){
            member = Member.builder()
                    .userToken(userToken)
                    .nickname(nickName)
                    .build();

            memberRepository.save(member);
        }
    }

    public JwtResponse login(String userToken, String nickName){
        setMember(userToken, nickName);
        return new JwtResponse(jwtUtil.createToken(userToken), jwtUtil.createRefreshToken());
    }
    public MainPageResponse getMainPage(String jwt) throws BaseException {

        String userToken = String.valueOf(jwtUtil.getUserIdx(jwtUtil.getJwt()));

        member = memberRepository.findByUserToken(userToken).orElse(null);

        return MainPageResponse.builder()
            .nickname(member.getNickname())
            .newClubResponse(getNewClubResponse())
            .notNewClubResponseList(getNotNewClubResponse())
            .build();
    }

    private NewClubResponse getNewClubResponse(){
        Optional<List<ClubRecruit>> recruits = clubRecruitRepository.findAllOrderByRecruitEndDate();

        // recruits가 존재하는 경우만 처리합니다.
        if (!recruits.isPresent()) {
            return null;
        }

        List<ClubRecruit> recruitList = recruits.get();
        LocalDate currentDate = LocalDate.now(); // 현재 날짜 가져오기

        // gen이 1이고, 현재 시각이 모집일보다 최대 7일 최소 0일 앞서는 동아리들을 필터링합니다.
        List<ClubRecruit> filteredRecruits = recruitList.stream()
                .filter(recruit -> recruit.getGen() == 1)
                .filter(recruit -> !currentDate.isBefore(recruit.getRecruitStartDate().minusDays(7)) && currentDate.isBefore(recruit.getRecruitStartDate()))
                .collect(Collectors.toList());

        // 랜덤으로 동아리 하나를 선택합니다.
        if (filteredRecruits.isEmpty()) {
            return null; // 조건에 맞는 동아리가 없는 경우
        }
        ClubRecruit selectedRecruit = filteredRecruits.get(new Random().nextInt(filteredRecruits.size()));

        // 해당 동아리의 Club 정보를 가져옵니다.
        Club club = clubRepository.findClubByClubId(selectedRecruit.getClubId().getClubId());

        // NewClubResponse에 정보를 할당하고 리턴합니다.
        return new NewClubResponse(club.getClubId(), club.getClubName(), club.getLogoUrl());
    }


    private List<NotNewClubResponse> getNotNewClubResponse() {
        Optional<List<ClubRecruit>> recruits = clubRecruitRepository.findAllOrderByRecruitEndDate();

        // recruits가 존재하는 경우만 처리합니다.
        if (!recruits.isPresent()) {
            return null;
        }

        List<ClubRecruit> recruitList = recruits.get();

        // clubId별로 가장 큰 gen 값을 가진 ClubRecruit 객체를 저장할 맵입니다.
        Map<Club, ClubRecruit> clubRecruitMap = new HashMap<>();

        for (ClubRecruit recruit : recruitList) {

            Club club = clubRepository.findClubByClubId(recruit.getClubId().getClubId()); // club 가져오기
            ClubRecruit existingRecruit = clubRecruitMap.get(club);

            // 해당 club에 대한 ClubRecruit 객체가 아직 없거나 gen 값이 더 큰 경우, 맵을 업데이트합니다.
            if (existingRecruit == null || recruit.getGen() > existingRecruit.getGen()) {
                clubRecruitMap.put(club, recruit);
            }
        }

        LocalDate currentDate = LocalDate.now(); // 현재 날짜 가져오기

        List<NotNewClubResponse> responses = clubRecruitMap.entrySet().stream()
                .map(entry -> {
                    Club club = entry.getKey();
                    ClubRecruit recruit = entry.getValue();

                    // recruit의 시작일과 종료일 가져오기
                    LocalDate recruitStartDate = recruit.getRecruitStartDate();
                    LocalDate recruitEndDate = recruit.getRecruitEndDate();

                    // recruitStatus 초기화
                    RecruitStatus recruitStatus;
                    if (currentDate.isBefore(recruitStartDate)) {
                        recruitStatus = RecruitStatus.SCHEDULED;
                    } else if (currentDate.isAfter(recruitStartDate) && currentDate.isBefore(recruitEndDate)) {
                        recruitStatus = RecruitStatus.RECRUITING;
                    } else {
                        recruitStatus = RecruitStatus.DEADLINE;
                    }

                    return NotNewClubResponse.builder()
                            .recruitId(recruit.getRecruitId())
                            .clubName(club.getClubName())
                            .gen(recruit.getGen())
                            .logourl(club.getLogoUrl())
                            .recruitStatus(recruitStatus)
                            .parts(recruit.getParts())
                            .build();
                })
                .collect(Collectors.toList());

        return responses;
    }
}



