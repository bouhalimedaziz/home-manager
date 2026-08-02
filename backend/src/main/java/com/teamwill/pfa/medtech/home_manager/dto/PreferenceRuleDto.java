package com.teamwill.pfa.medtech.home_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreferenceRuleDto {
    private Long id;
    private Long userId;
    private Long deviceId;
    private String deviceName;
    private String condition;
    private String action;
    private boolean strict;
    private boolean enabled;
    private Instant createdAt;
}
