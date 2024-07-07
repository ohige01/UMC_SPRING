package com.example.demo.service.StoreService;

import com.example.demo.domain.Member;
import com.example.demo.domain.Region;
import com.example.demo.domain.Review;
import com.example.demo.domain.Store;
import com.example.demo.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store getStore(Long storeId);
    Store joinStore(StoreRequestDTO.JoinStoreDto request, Region region);
    Review writeReview(StoreRequestDTO.ReviewDto request, Store store, Member member);
}
