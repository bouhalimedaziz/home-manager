package com.teamwill.pfa.medtech.home_manager.service;

import com.teamwill.pfa.medtech.home_manager.dto.SmartCurtainDto;

import java.util.List;

public interface SmartCurtainService {
    SmartCurtainDto createSmartCurtain(SmartCurtainDto dto);
    List<SmartCurtainDto> getAllSmartCurtains();
    SmartCurtainDto getSmartCurtainById(Long id);
    SmartCurtainDto updateSmartCurtain(Long id, SmartCurtainDto dto);
    void deleteSmartCurtain(Long id);
}
