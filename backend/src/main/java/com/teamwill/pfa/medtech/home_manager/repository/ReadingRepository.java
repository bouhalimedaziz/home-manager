package com.teamwill.pfa.medtech.home_manager.repository;

import com.teamwill.pfa.medtech.home_manager.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
    List<Reading> findTop50ByDeviceIdOrderByRecordedAtDesc(Long deviceId);
}
