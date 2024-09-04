package com.example.demo.repository;

import com.example.demo.domain.Mission;
import com.example.demo.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);
}
