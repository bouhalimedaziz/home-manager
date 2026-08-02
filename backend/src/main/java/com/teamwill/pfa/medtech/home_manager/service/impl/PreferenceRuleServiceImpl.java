package com.teamwill.pfa.medtech.home_manager.service.impl;

import com.teamwill.pfa.medtech.home_manager.dto.PreferenceRuleDto;
import com.teamwill.pfa.medtech.home_manager.entity.PreferenceRule;
import com.teamwill.pfa.medtech.home_manager.exception.ResourceNotFoundException;
import com.teamwill.pfa.medtech.home_manager.mapper.PreferenceRuleMapper;
import com.teamwill.pfa.medtech.home_manager.repository.PreferenceRuleRepository;
import com.teamwill.pfa.medtech.home_manager.service.PreferenceRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PreferenceRuleServiceImpl implements PreferenceRuleService {

    private final PreferenceRuleRepository preferenceRuleRepository;

    @Override
    public PreferenceRuleDto createRule(PreferenceRuleDto dto) {
        PreferenceRule rule = PreferenceRuleMapper.mapToEntity(dto);
        rule.setCreatedAt(Instant.now());
        return PreferenceRuleMapper.mapToDto(preferenceRuleRepository.save(rule));
    }

    @Override
    public List<PreferenceRuleDto> getRulesForUser(Long userId) {
        return preferenceRuleRepository.findByUserId(userId)
                .stream()
                .map(PreferenceRuleMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PreferenceRuleDto updateRule(Long id, PreferenceRuleDto dto) {
        PreferenceRule existing = preferenceRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Preference rule not found with id: " + id));

        existing.setDeviceId(dto.getDeviceId());
        existing.setDeviceName(dto.getDeviceName());
        existing.setCondition(dto.getCondition());
        existing.setAction(dto.getAction());
        existing.setStrict(dto.isStrict());
        existing.setEnabled(dto.isEnabled());

        return PreferenceRuleMapper.mapToDto(preferenceRuleRepository.save(existing));
    }

    @Override
    public void deleteRule(Long id) {
        if (!preferenceRuleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Preference rule not found with id: " + id);
        }
        preferenceRuleRepository.deleteById(id);
    }
}
