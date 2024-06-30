package com.example.demo.service.StoreService;

import com.example.demo.apiPayload.code.status.ErrorStatus;
import com.example.demo.apiPayload.exception.handler.StoreHandler;
import com.example.demo.converter.StoreConverter;
import com.example.demo.domain.Member;
import com.example.demo.domain.Region;
import com.example.demo.domain.Review;
import com.example.demo.domain.Store;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.web.dto.StoreRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    @Override
    public Store getStore(Long storeId) {

        return storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
    }
    @Override
    @Transactional
    public Store joinStore(StoreRequestDTO.JoinDto request, Region region) {

        Store newStore = StoreConverter.toStore(request, region);

        return storeRepository.save(newStore);
    }
    @Override
    @Transactional
    public Review writeReview(StoreRequestDTO.ReviewDto request, Store store, Member member){

        Review newReview = StoreConverter.toReview(request, store, member);

        return reviewRepository.save(newReview);
    }
}
