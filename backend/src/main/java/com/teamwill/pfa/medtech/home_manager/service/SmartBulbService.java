package com.teamwill.pfa.medtech.home_manager.service;

import com.teamwill.pfa.medtech.home_manager.dto.SmartBulbDto;

import java.util.List;

public interface SmartBulbService {
    SmartBulbDto createSmartBulb(SmartBulbDto dto);
    List<SmartBulbDto> getAllSmartBulbs();
    SmartBulbDto getSmartBulbById(Long id);
    SmartBulbDto updateSmartBulb(Long id, SmartBulbDto dto);
    void deleteSmartBulb(Long id);
}
