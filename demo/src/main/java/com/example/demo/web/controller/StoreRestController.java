package com.example.demo.web.controller;

import com.example.demo.apiPayload.ApiResponse;
import com.example.demo.converter.StoreConverter;
import com.example.demo.domain.Region;
import com.example.demo.domain.Store;
import com.example.demo.service.RegionService.RegionCommandService;
import com.example.demo.service.StoreService.StoreCommandService;
import com.example.demo.web.dto.StoreRequestDTO;
import com.example.demo.web.dto.StoreResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final RegionCommandService regionCommandService;
    @PostMapping("/register")
    public ApiResponse<StoreResponseDTO.JoinResultDTO> join(@RequestBody @Valid StoreRequestDTO.JoinDto request){
        Region region = regionCommandService.getRegion(request.getRegionId());
        Store store = storeCommandService.joinStore(request, region);
        return ApiResponse.onSuccess(StoreConverter.toJoinResultDTO(store));
    }
}
