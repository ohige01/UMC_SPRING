package com.example.demo.service.StoreService;

import com.example.demo.domain.Mission;
import com.example.demo.domain.Review;
import com.example.demo.domain.Store;
import com.example.demo.repository.MissionRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {

        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page - 1, 10));
        return StorePage;
    }
    @Override
    public Page<Mission> getMissionList(Long StoreId, Integer page) {

        Store store = storeRepository.findById(StoreId).get();

        Page<Mission> MissionPage = missionRepository.findAllByStore(store, PageRequest.of(page - 1, 10));
        return MissionPage;
    }
    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }
}
