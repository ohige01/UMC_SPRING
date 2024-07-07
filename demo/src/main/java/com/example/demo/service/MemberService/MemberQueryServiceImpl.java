package com.example.demo.service.MemberService;

import com.example.demo.domain.Member;
import com.example.demo.domain.Review;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryServiceImpl implements MemberQueryService{
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    @Override
    public Page<Review> getReviewList(Long MemberId, Integer page) {

        Member member = memberRepository.findById(MemberId).get();
        Page<Review> memberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return memberPage;
    }
}
