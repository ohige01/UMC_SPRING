package com.example.demo.service.MemberService;

import com.example.demo.converter.MemberConverter;
import com.example.demo.domain.Member;
import com.example.demo.domain.Mission;
import com.example.demo.domain.Review;
import com.example.demo.domain.enums.MissionStatus;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.repository.MemMissionRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryServiceImpl implements MemberQueryService{
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MemMissionRepository memMissionRepository;
    @Override
    @Transactional
    public MemberMission memberMission(MemberRequestDTO.MemMissionDto request, Member member, Mission mission){
        MemberMission newMission = MemberConverter.toMemMission(member, mission);
        return memMissionRepository.save(newMission);
    }
    @Override
    public Page<Review> getReviewList(Long MemberId, Integer page) {

        Member member = memberRepository.findById(MemberId).get();
        Page<Review> memberPage = reviewRepository.findAllByMember(member, PageRequest.of(page - 1, 10));
        return memberPage;
    }
    @Override
    public Page<MemberMission> getMissionList(Long MemberId, Integer page) {

        Member member = memberRepository.findById(MemberId).get();
        List<MemberMission> memberMission = memMissionRepository.findAllByMember(member).stream()
                .filter(mission -> mission.getStatus().equals(MissionStatus.CHALLENGING))
                .toList();

        //List to Page
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()),memberMission.size());
        Page<MemberMission> missionPage = new PageImpl<>(memberMission.subList(start,end), pageRequest, memberMission.size());

        return missionPage;
    }
}
