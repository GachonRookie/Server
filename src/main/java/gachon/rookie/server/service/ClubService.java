package gachon.rookie.server.service;

import gachon.rookie.server.common.BaseErrorCode;
import gachon.rookie.server.common.BaseException;
import gachon.rookie.server.dto.*;
import gachon.rookie.server.entity.*;
import gachon.rookie.server.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final ClubRecruitRepository recruitRepository;
    private final MemberRepository memberRepository;
    private final ClubApplyRepository applyRepository;
    private final ClubReportRepository reportRepository;
    private final ClubPartRepository partRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository, ClubRecruitRepository recruitRepository, MemberRepository memberRepository, ClubApplyRepository applyRepository, ClubReportRepository reportRepository, ClubPartRepository partRepository) {
        this.clubRepository = clubRepository;
        this.recruitRepository = recruitRepository;
        this.memberRepository = memberRepository;
        this.applyRepository = applyRepository;
        this.reportRepository = reportRepository;
        this.partRepository = partRepository;
    }

    /**
     * 메인 동아리 상세 API
     * */
    @Transactional
    public GetMainClubDetailRes getMainClubDetail(Long clubId) throws BaseException {
        // 탐색 대상 동아리 엔티티 가져오기
        Club target = clubRepository.findByIdWhereStatusActive(clubId)
                .orElseThrow(() -> new BaseException(BaseErrorCode.CLUB_NOT_EXIST));

        //가장 최근 것을 가지고오기 위한 페이징
        Pageable limitOne = PageRequest.of(0, 1);

        //동아리 모집 엔티티 가져오기 단, status가 ACTIVE한 것 중에서 가장 최근인 것 (Defensive Code)
        List<ClubRecruit> recruit = recruitRepository.findByClubId(target, limitOne)
                .orElseThrow(() -> new BaseException(BaseErrorCode.RECRUIT_NOT_EXIST));

        //Stream API를 이용해 ClubPart 엔티티 리스트에서 파트 이름 리스트 뽑아내기
        List<String> parts = recruit.get(0).getParts().stream().map(ClubPart::getPartName).toList();


        // 결과 DTO 리턴
        return GetMainClubDetailRes.builder()
                .clubName(target.getClubName())
                .activityEndDate(recruit.get(0).getActivityEndDate().toString())
                .activityStartDate(recruit.get(0).getActivityStartDate().toString())
                .applyStartDate(recruit.get(0).getRecruitStartDate().toString())
                .applyEndDate(recruit.get(0).getRecruitEndDate().toString())
                .target(recruit.get(0).getTarget())
                .parts(parts).build();
    }

    /**
     * 동아리 지원(찜) API - Service
     * */
    @Transactional
    public PostApplyRes postApplyToClub(PostApplyReq req, String userToken) throws BaseException {
        // 탐색 대상 동아리 엔티티 가져오기
        Club target = clubRepository.findByIdWhereStatusActive(req.getClubId())
                .orElseThrow(() -> new BaseException(BaseErrorCode.CLUB_NOT_EXIST));

        //지원하는 맴버 가져오기
        Member applyMember = memberRepository.findByUserToken(userToken).orElseThrow(()-> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        //이미 지원한 동아리인지 확인
        boolean isApplyExist = applyRepository.existsByClubIdAndMemberId(target, applyMember);
        if(isApplyExist) throw new BaseException(BaseErrorCode.APPLY_EXIST);

        //지원 대상 공고 가져오기
        Pageable limitOne = PageRequest.of(0, 1);
        List<ClubRecruit> recruit = recruitRepository.findByClubId(target, limitOne)
                .orElseThrow(() -> new BaseException(BaseErrorCode.RECRUIT_NOT_EXIST));

        //지원 내용 저장
        applyRepository.save(
                ClubApply.builder()
                .memberId(applyMember)
                .clubId(target)
                .gen(recruit.get(0).getGen())
                .applyPart(req.getPart())
                        .applyStatus(ApplyStatus.STANDBY)
                        .build()
        );

        //리턴
        return new PostApplyRes("등록에 성공하였습니다.");
    }

    /**
     * 문의/등록 페이지 API
     * */

    /**
     * 동아리 등록 - Service
     * */
    @Transactional
    public PostClubRes postClub(PostClubReq req) throws BaseException {

        Integer preValidClubExist = clubRepository.preValidClubExist(req.getClubName());
        log.info(preValidClubExist.toString());
        if(preValidClubExist > 0) throw new BaseException(BaseErrorCode.CLUB_EXIST);
        //동아리 빌드
        Club club = Club.builder()
                .clubName(req.getClubName())
                .clubTag( (req.getClubType() == 0) ? ClubTag.INTERNAL : ClubTag.UNION)
                .adContent(req.getContent())
                .snsLink(req.getApplyLink())
                .build();
        //동아리 상태 Invalid 로 바꾸기
        club.updateStatusToInvalid();

        //저장
        clubRepository.save(club);

        Integer preClubExist = clubRepository.preClubExist(club.getClubName());

        int clubGen = 0;

        if(preClubExist != 0) clubGen = preClubExist + 1;

        List<String> parts = req.getPartList();

        for (String part : parts) {
            partRepository.save(
                    ClubPart.builder()
                    .clubId(club)
                    .gen(clubGen)
                    .partName(part)
                    .build()
            );
        }

        return new PostClubRes("동아리 추가에 성공하였습니다.");
    }
    /**
     * 문의/등록 동아리 상세 - Service
     * */
    @Transactional
    public GetRegisterDetailRes getReports(Long req) throws BaseException {

        //동아리 가져오기
        Club club = clubRepository.findById(req)
                .orElseThrow(() -> new BaseException(BaseErrorCode.CLUB_NOT_EXIST));

        //리포트 엔티티 리스트 가져오기
        List<ClubReport> reportList = reportRepository.findReportListByClubId(club)
                .orElseThrow(() -> new BaseException(BaseErrorCode.REPORT_NOT_EXIST));

        //동아리 모집 가져오기
        ClubRecruit recruit = recruitRepository.findById(req)
                .orElseThrow(() -> new BaseException(BaseErrorCode.RECRUIT_NOT_EXIST));

        //리포트 리스트 가져오기
        List<Report> reports = reportList.stream()
                .map(
                        report -> Report.builder()
                                .authorName(report.getMemberId().getNickname())
                                .content(report.getContent())
                                .createdAt(report.getCreatedAt().toString())
                                .build()
                ).toList();

        //모집 파트 가져오기
        List<String> parts = recruit.getParts().stream().map(ClubPart::getPartName).toList();

        //리턴
        return GetRegisterDetailRes.builder()
                .clubName(reportList.get(0).getClubId().getClubName())
                .applyLink(reportList.get(0).getClubId().getSnsLink())
                .activityStart(recruit.getActivityStartDate().toString())
                .activityEnd(recruit.getActivityEndDate().toString())
                .target(recruit.getTarget())
                .parts(parts)
                .reports(reports)
                .build();

    }
    /**
     * 동아리 리포트 작성 API - Service
     * */
    @Transactional
    public PostReportRes postReport(PostReportReq req, String userToken) throws BaseException {
        // 탐색 대상 동아리 엔티티 가져오기
        Club target = clubRepository.findByIdWhereStatusActive(req.getClubId())
                .orElseThrow(() -> new BaseException(BaseErrorCode.CLUB_NOT_EXIST));

        //작성하는 맴버 가져오기
        Member author = memberRepository.findByUserToken(userToken).orElseThrow(()-> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        reportRepository.save(
                ClubReport.builder()
                        .clubId(target)
                        .memberId(author)
                        .content(req.getContent())
                        .build()
        );

        return new PostReportRes("작성에 성공하였습니다.");

    }


}
