package com.example.demo.web.controller;

import com.example.demo.apiPayload.ApiResponse;
import com.example.demo.converter.MemberConverter;
import com.example.demo.domain.Member;
import com.example.demo.domain.Mission;
import com.example.demo.domain.Review;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.service.MemberService.MemberCommandService;
import com.example.demo.service.MemberService.MemberQueryService;
import com.example.demo.service.MissionService.MissionCommandService;
import com.example.demo.validation.annotation.ExistPage;
import com.example.demo.web.dto.MemberRequestDTO;
import com.example.demo.web.dto.MemberResponseDTO;
import com.example.demo.web.dto.MissionResponseDTO;
import com.example.demo.web.dto.StoreResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MissionCommandService missionCommandService;
    private final MemberQueryService memberQueryService;
    @PostMapping("/join")
    @Operation(summary = "회원가입 API")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/memMission")
    @Operation(summary = "특정 유저의 미션 등록 API")
    public ApiResponse<MemberResponseDTO.MemMissionResultDTO> memMission(@RequestBody @Valid MemberRequestDTO.MemMissionDto request) {
        Member member = memberCommandService.getMember(request.getUserId());
        Mission mission = missionCommandService.getMission(request.getMissionId());
        MemberMission memberMission = memberQueryService.memberMission(request, member, mission);
        return ApiResponse.onSuccess(MemberConverter.toMemMissionResultDTO(memberMission));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "특정 유저의 리뷰 목록 조회 API")
    @Parameters({
            @Parameter(name = "memberId", description = "유저의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다."),
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@PathVariable(name = "memberId") Long memberId, @ExistPage @RequestParam(name = "page") Integer page){
        Page<Review> reviews = memberQueryService.getReviewList(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.reviewPreViewListDTO(reviews));
    }
    @GetMapping("/{memberId}/missions")
    @Operation(summary = "특정 유저의 도전중인 미션 목록 조회 API")
    @Parameters({
            @Parameter(name = "memberId", description = "유저의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다."),
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(@PathVariable(name = "memberId") Long memberId, @ExistPage @RequestParam(name = "page") Integer page){
        Page<MemberMission> missions = memberQueryService.getMissionList(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.missionPreViewListDTO(missions));
    }
}