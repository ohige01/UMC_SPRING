package com.example.demo.converter;

import com.example.demo.domain.Member;
import com.example.demo.domain.Region;
import com.example.demo.domain.Review;
import com.example.demo.domain.Store;
import com.example.demo.web.dto.StoreRequestDTO;
import com.example.demo.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {
    public static StoreResponseDTO.JoinResultDTO toJoinResultDTO(Store store){
        return StoreResponseDTO.JoinResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static StoreResponseDTO.WriteResultDTO toWriteResultDTO(Review review){
        return StoreResponseDTO.WriteResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Store toStore(StoreRequestDTO.JoinDto request, Region region){
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region_id(region)
                .build();
    }
    public static Review toReview(StoreRequestDTO.ReviewDto request, Store store, Member member){
        return Review.builder()
                .store(store)
                .member(member)
                .title(request.getTitle())
                .score(request.getScore())
                .build();
    }
}
