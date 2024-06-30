package com.example.demo.service.StoreService;

import com.example.demo.domain.Region;
import com.example.demo.domain.Store;
import com.example.demo.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store joinStore(StoreRequestDTO.JoinDto request, Region region);
}
