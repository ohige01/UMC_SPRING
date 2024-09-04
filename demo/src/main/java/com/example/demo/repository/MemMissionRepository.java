package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.domain.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemMissionRepository extends JpaRepository<MemberMission, Long> {
    List<MemberMission> findAllByMember(Member member);
}
