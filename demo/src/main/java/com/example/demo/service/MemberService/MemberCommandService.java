package com.example.demo.service.MemberService;

import com.example.demo.domain.Member;
import com.example.demo.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    Member getMember(Long memberId);
    Member joinMember(MemberRequestDTO.JoinDto request);

}
