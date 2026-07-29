package com.teamwill.pfa.medtech.home_manager.service;

import com.teamwill.pfa.medtech.home_manager.dto.SmartACDto;

import java.util.List;

public interface SmartACService {
    SmartACDto createSmartAC(SmartACDto dto);
    List<SmartACDto> getAllSmartACs();
    SmartACDto getSmartACById(Long id);
    SmartACDto updateSmartAC(Long id, SmartACDto dto);
    void deleteSmartAC(Long id);
}
