package com.example.demo.web.controller;

import com.example.demo.apiPayload.ApiResponse;
import com.example.demo.converter.StoreConverter;
import com.example.demo.domain.Member;
import com.example.demo.domain.Region;
import com.example.demo.domain.Review;
import com.example.demo.domain.Store;
import com.example.demo.service.MemberService.MemberCommandService;
import com.example.demo.service.RegionService.RegionCommandService;
import com.example.demo.service.StoreService.StoreCommandService;
import com.example.demo.web.dto.StoreRequestDTO;
import com.example.demo.web.dto.StoreResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final RegionCommandService regionCommandService;
    private final MemberCommandService memberCommandService;
    @PostMapping("/register")
    public ApiResponse<StoreResponseDTO.JoinResultDTO> register(@RequestBody @Valid StoreRequestDTO.JoinDto request){
        Region region = regionCommandService.getRegion(request.getRegionId());
        Store store = storeCommandService.joinStore(request, region);
        return ApiResponse.onSuccess(StoreConverter.toJoinResultDTO(store));
    }
    @PostMapping("/review")
    public ApiResponse<StoreResponseDTO.WriteResultDTO> review(@RequestBody @Valid StoreRequestDTO.ReviewDto request){
        Store store = storeCommandService.getStore(request.getStoreId());
        Member member = memberCommandService.getMember(request.getMemberId());
        Review review = storeCommandService.writeReview(request, store, member);
        return ApiResponse.onSuccess(StoreConverter.toWriteResultDTO(review));
    }
}
