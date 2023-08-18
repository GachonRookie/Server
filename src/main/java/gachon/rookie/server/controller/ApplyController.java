package gachon.rookie.server.controller;

import gachon.rookie.server.common.BaseException;
import gachon.rookie.server.dto.GetMyPageResponse;
import gachon.rookie.server.entity.ApplyStatus;
import gachon.rookie.server.service.ApplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Tag(name = "지원")
@RequiredArgsConstructor
@RestController
@RequestMapping("/apply")
public class ApplyController {

    private final ApplyService applyService;

    @Operation(summary = "마이 페이지", description = "유저의 마이 페이지입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping("/mypage")
    public GetMyPageResponse getMyPage(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt) throws BaseException {

        return applyService.getMyPage();
    }

    @Operation(summary = "마이 페이지 심사 결과 업데이트", description = "심사 결과를 업데이트합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500")
    })
    @PostMapping("/update")
    public void updateStatus(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt, @RequestBody ApplyStatus applyStatus, @RequestBody Long applyId) throws BaseException {
        applyService.updateStatus(applyStatus, applyId);
    }

}
