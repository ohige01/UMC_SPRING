package com.example.demo.converter;

import com.example.demo.domain.Member;
import com.example.demo.domain.Mission;
import com.example.demo.domain.Review;
import com.example.demo.domain.enums.Gender;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.web.dto.MemberRequestDTO;
import com.example.demo.web.dto.MemberResponseDTO;
import com.example.demo.web.dto.MissionResponseDTO;
import com.example.demo.web.dto.StoreResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getStore().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getTitle())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(MemberConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static MissionResponseDTO.MissionPreViewDTO missionPreViewDTO(MemberMission mission){
        return MissionResponseDTO.MissionPreViewDTO.builder()
                .ownerNickname(mission.getMission().getStore().getName())
                .reward(mission.getMission().getReward())
                .body(mission.getMission().getMissionSpec())
                .build();
    }
    public static MissionResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<MemberMission> missionList){

        List<MissionResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(MemberConverter::missionPreViewDTO).collect(Collectors.toList());

        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}