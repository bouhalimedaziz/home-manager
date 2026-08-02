package com.teamwill.pfa.medtech.home_manager.service;

import com.teamwill.pfa.medtech.home_manager.dto.PreferenceRuleDto;

import java.util.List;

public interface PreferenceRuleService {
    PreferenceRuleDto createRule(PreferenceRuleDto dto);
    List<PreferenceRuleDto> getRulesForUser(Long userId);
    PreferenceRuleDto updateRule(Long id, PreferenceRuleDto dto);
    void deleteRule(Long id);
}
