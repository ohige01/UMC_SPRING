package com.example.demo.service.MemberService;

import com.example.demo.domain.Member;
import com.example.demo.domain.Mission;
import com.example.demo.domain.Review;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.web.dto.MemberRequestDTO;
import org.springframework.data.domain.Page;

public interface MemberQueryService {
    Page<Review> getReviewList(Long MemberId, Integer page);
    Page<MemberMission> getMissionList(Long MemberId, Integer page);
    MemberMission memberMission(MemberRequestDTO.MemMissionDto request, Member member, Mission mission);
}
