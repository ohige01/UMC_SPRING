package com.example.demo.repository;

import com.example.demo.domain.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemMissionRepository extends JpaRepository<MemberMission, Long> {
}
