package com.example.demo.service.StoreService;

import com.example.demo.domain.Mission;
import com.example.demo.domain.Review;
import com.example.demo.domain.Store;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StoreQueryService {
    Page<Review> getReviewList(Long StoreId, Integer page);
    Page<Mission> getMissionList(Long StoreId, Integer page);
    Optional<Store> findStore(Long id);
}
