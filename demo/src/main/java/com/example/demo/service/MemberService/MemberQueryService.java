package com.example.demo.service.MemberService;

import com.example.demo.domain.Review;
import org.springframework.data.domain.Page;

public interface MemberQueryService {
    Page<Review> getReviewList(Long MemberId, Integer page);
}
