package com.example.demo.converter;

import com.example.demo.domain.Member;
import com.example.demo.domain.Mission;
import com.example.demo.domain.enums.Gender;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.web.dto.MemberRequestDTO;
import com.example.demo.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Member toMember(MemberRequestDTO.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDTO.MemMissionResultDTO toMemMissionResultDTO(MemberMission mission){
        return MemberResponseDTO.MemMissionResultDTO.builder()
                .id(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemMission(Member member, Mission mission){
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }
}