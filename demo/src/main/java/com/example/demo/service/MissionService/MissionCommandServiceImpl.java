package com.example.demo.service.MissionService;

import com.example.demo.domain.Mission;
import com.example.demo.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;

    @Override
    public Mission getMission(Long missionId) {
        return missionRepository.findById(missionId).get();
    }
}
