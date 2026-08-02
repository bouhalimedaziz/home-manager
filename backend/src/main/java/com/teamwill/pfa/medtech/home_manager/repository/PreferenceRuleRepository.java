package com.teamwill.pfa.medtech.home_manager.repository;

import com.teamwill.pfa.medtech.home_manager.entity.PreferenceRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreferenceRuleRepository extends JpaRepository<PreferenceRule, Long> {
    List<PreferenceRule> findByUserId(Long userId);
}
