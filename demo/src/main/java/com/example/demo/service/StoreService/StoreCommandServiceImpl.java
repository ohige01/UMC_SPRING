package com.example.demo.service.StoreService;

import com.example.demo.converter.StoreConverter;
import com.example.demo.domain.Region;
import com.example.demo.domain.Store;
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
    @Override
    public Store joinStore(StoreRequestDTO.JoinDto request, Region region) {

        Store newStore = StoreConverter.toStore(request, region);

        return storeRepository.save(newStore);
    }
}
