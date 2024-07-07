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
import com.example.demo.service.StoreService.StoreQueryService;
import com.example.demo.validation.annotation.ExistStore;
import com.example.demo.web.dto.StoreRequestDTO;
import com.example.demo.web.dto.StoreResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final RegionCommandService regionCommandService;
    private final MemberCommandService memberCommandService;
    private final StoreQueryService storeQueryService;
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
    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page){
        Page<Review> reviews = storeQueryService.getReviewList(storeId,page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviews));
    }
}
