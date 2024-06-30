package com.example.demo.service.RegionService;

import com.example.demo.domain.Region;
import com.example.demo.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegionCommandServiceImpl implements RegionCommandService{
    private final RegionRepository regionRepository;
    @Override
    public Region getRegion(Long regionId) {
        return regionRepository.findById(regionId).get();
    }
}
