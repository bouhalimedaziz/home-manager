package com.teamwill.pfa.medtech.home_manager.mapper;

import com.teamwill.pfa.medtech.home_manager.dto.PreferenceRuleDto;
import com.teamwill.pfa.medtech.home_manager.entity.PreferenceRule;

public class PreferenceRuleMapper {

    public static PreferenceRuleDto mapToDto(PreferenceRule rule) {
        return PreferenceRuleDto.builder()
                .id(rule.getId())
                .userId(rule.getUserId())
                .deviceId(rule.getDeviceId())
                .deviceName(rule.getDeviceName())
                .condition(rule.getCondition())
                .action(rule.getAction())
                .strict(rule.isStrict())
                .enabled(rule.isEnabled())
                .createdAt(rule.getCreatedAt())
                .build();
    }

    public static PreferenceRule mapToEntity(PreferenceRuleDto dto) {
        return PreferenceRule.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .deviceId(dto.getDeviceId())
                .deviceName(dto.getDeviceName())
                .condition(dto.getCondition())
                .action(dto.getAction())
                .strict(dto.isStrict())
                .enabled(dto.isEnabled())
                .build();
    }
}
