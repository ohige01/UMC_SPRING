package com.example.demo.web.controller;

import com.example.demo.apiPayload.ApiResponse;
import com.example.demo.converter.MemberConverter;
import com.example.demo.domain.Member;
import com.example.demo.domain.Mission;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.service.MemberService.MemberCommandService;
import com.example.demo.service.MissionService.MissionCommandService;
import com.example.demo.web.dto.MemberRequestDTO;
import com.example.demo.web.dto.MemberResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MissionCommandService missionCommandService;
    @PostMapping("/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/memMission")
    public ApiResponse<MemberResponseDTO.MemMissionResultDTO> memMission(@RequestBody @Valid MemberRequestDTO.MemMissionDto request) {
        Member member = memberCommandService.getMember(request.getUserId());
        Mission mission = missionCommandService.getMission(request.getMissionId());
        MemberMission memberMission = memberCommandService.memberMission(request, member, mission);
        return ApiResponse.onSuccess(MemberConverter.toMemMissionResultDTO(memberMission));
    }
}