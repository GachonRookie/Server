package gachon.rookie.server.controller;

import gachon.rookie.server.common.BaseException;
import gachon.rookie.server.common.BaseResponse;
import gachon.rookie.server.dto.*;
import gachon.rookie.server.service.ClubService;
import gachon.rookie.server.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "요청에 성공하였습니다.")})
@RequestMapping("/api/clubs")
public class ClubController {

    private final JwtUtil jwtUtil;

    private final ClubService clubService;

    @Autowired
    public ClubController(JwtUtil jwtUtil, ClubService clubService) {
        this.jwtUtil = jwtUtil;
        this.clubService = clubService;
    }

    /**
     * 메인 동아리 상세 API
     * */
    @Operation(summary = "메인 동아리 상세 API", description = "메인 페이지에서 동아리 터치했을때 나오는 상세 페이지")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "유효한 JWT가 아닙니다.",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "404", description = "대상 동아리가 존재하지 않습니다. | 동아리 모집이 존재하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected Error | JWT ERROR",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @GetMapping(value = "/main/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<GetMainClubDetailRes> getMainClubDetail(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt, @RequestParam("club-id") Long clubId) throws BaseException {
        String memberIdx = jwtUtil.getUserIdx(jwtUtil.getJwt());
        clubService.checkMemberValid(memberIdx);

        return new BaseResponse<>(clubService.getMainClubDetail(clubId));

    }
    /**
     * 동아리 지원(찜) API
     * */
    @Operation(summary = "동아리 지원(찜) API", description = "동아리 모의 지원 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "유효한 JWT가 아닙니다.",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "404", description = "대상 동아리가 존재하지 않습니다. | 동아리 모집이 존재하지 않습니다. | 유저가 존재하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "409", description = "이미 지원한 동아리 입니다.",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected Error | JWT ERROR",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @PostMapping(value = "/apply", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PostApplyRes> postApplyToClub(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt, @RequestBody PostApplyReq req) throws BaseException {

        String memberIdx = jwtUtil.getUserIdx(jwtUtil.getJwt());
        clubService.checkMemberValid(memberIdx);

        String userToken = jwtUtil.getUserIdx(jwtUtil.getJwt());

        return new BaseResponse<>(clubService.postApplyToClub(req, userToken));
    }


    /**
     * 문의/등록 페이지 API
     * */
    @Operation(summary = "문의/등록 페이지 API", description = "문의/등록 페이지 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "유효한 JWT가 아닙니다.",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected Error | JWT ERROR",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @GetMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<GetClubListRes> getClubList(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt) throws BaseException {
        String memberIdx = jwtUtil.getUserIdx(jwtUtil.getJwt());
        clubService.checkMemberValid(memberIdx);

        return new BaseResponse<>(clubService.getClubList());
    }

    /**
     * 동아리 등록
     * */
    @Operation(summary = "동아리 등록 API", description = "동아리 등록 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "유효한 JWT가 아닙니다.",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "409", description = "이미 동아리가 존재합니다.",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected Error | JWT ERROR",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PostClubRes> postClub(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt, @RequestBody PostClubReq req) throws BaseException {
        //Jwt Validation
        String memberIdx = jwtUtil.getUserIdx(jwtUtil.getJwt());
        clubService.checkMemberValid(memberIdx);

        return new BaseResponse<>(clubService.postClub(req));
    }

    /**
     * 문의/등록 동아리 상세
     * */
    @Operation(summary = "문의/등록 동아리 상세 API", description = "문의/등록 동아리 상세 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "유효한 JWT가 아닙니다.",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "404", description = "대상 동아리가 존재하지 않습니다. | 리포트가 존재하지 않습니다. | 동아리 모집이 존재하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected Error | JWT ERROR",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @GetMapping(value = "/register/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<GetRegisterDetailRes> getReports(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt, @RequestParam("club-id") Long clubId) throws BaseException {
        String memberIdx = jwtUtil.getUserIdx(jwtUtil.getJwt());
        clubService.checkMemberValid(memberIdx);

        return new BaseResponse<>(clubService.getReports(clubId));
    }
    /**
     * 동아리 리포트 작성 API
     * */
    @Operation(summary = "동아리 리포트 작성 API", description = "동아리 리포트 작성 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "유효한 JWT가 아닙니다.",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "404", description = "대상 동아리가 존재하지 않습니다. | 유저가 존재하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected Error | JWT ERROR",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @PostMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PostReportRes> postReport(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt, @RequestBody PostReportReq req) throws BaseException {
        String memberIdx = jwtUtil.getUserIdx(jwtUtil.getJwt());
        clubService.checkMemberValid(memberIdx);

        String userToken = jwtUtil.getUserIdx(jwtUtil.getJwt());

        return new BaseResponse<>(clubService.postReport(req, userToken));
    }
}
