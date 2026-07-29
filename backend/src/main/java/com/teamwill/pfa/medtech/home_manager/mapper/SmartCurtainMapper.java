package com.teamwill.pfa.medtech.home_manager.mapper;

import com.teamwill.pfa.medtech.home_manager.dto.SmartCurtainDto;
import com.teamwill.pfa.medtech.home_manager.entity.SmartCurtain;

public class SmartCurtainMapper {

    public static SmartCurtainDto mapToDto(SmartCurtain device) {
        return SmartCurtainDto.builder()
                .id(device.getId())
                .name(device.getName())
                .unit(device.getUnit())
                .status(device.getStatus())
                .data(device.getData())
                .build();
    }

    public static SmartCurtain mapToEntity(SmartCurtainDto dto) {
        return SmartCurtain.builder()
                .id(dto.getId())
                .name(dto.getName())
                .unit(dto.getUnit())
                .status(dto.getStatus())
                .data(dto.getData())
                .build();
    }
}
