package gachon.rookie.server.controller;

import gachon.rookie.server.dto.JwtResponse;
import gachon.rookie.server.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MemberController")
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final JwtUtil jwtUtil;

    @Operation(summary = "유저 로그인", description = "애플 로그인을 통해 발급한 토큰으로 유저에게 accessToken과 refreshToken을 발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500")
    })
    @PostMapping("/login")
    public JwtResponse login(String userToken) {
        return new JwtResponse(jwtUtil.createToken(userToken), jwtUtil.createRefreshToken());
    }

}
