package gachon.rookie.server.controller;

import gachon.rookie.server.common.BaseException;
import gachon.rookie.server.dto.JwtResponse;
import gachon.rookie.server.dto.MainPageResponse;
import gachon.rookie.server.service.MemberService;
import gachon.rookie.server.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Tag(name = "멤버")
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "유저 로그인", description = "애플 로그인을 통해 발급한 userId로 유저에게 accessToken과 refreshToken을 발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500")
    })
    @PostMapping("/login")
    public JwtResponse login(String userToken, String nickName) {

        return memberService.login(userToken, nickName);
    }

    @Operation(summary = "메인 페이지", description = "유저의 메인 페이지입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping("/main")
    public MainPageResponse getMainPage(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt) throws BaseException {

        return memberService.getMainPage(jwtUtil.getJwt());
    }



}
