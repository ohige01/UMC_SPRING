package com.example.demo.service.StoreService;

import com.example.demo.domain.Review;
import org.springframework.data.domain.Page;

public interface StoreQueryService {
    Page<Review> getReviewList(Long StoreId, Integer page);
}
