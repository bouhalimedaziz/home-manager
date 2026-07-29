package com.teamwill.pfa.medtech.home_manager.mapper;

import com.teamwill.pfa.medtech.home_manager.dto.SmartACDto;
import com.teamwill.pfa.medtech.home_manager.entity.SmartAC;

public class SmartACMapper {

    public static SmartACDto mapToDto(SmartAC device) {
        return SmartACDto.builder()
                .id(device.getId())
                .name(device.getName())
                .unit(device.getUnit())
                .status(device.getStatus())
                .data(device.getData())
                .build();
    }

    public static SmartAC mapToEntity(SmartACDto dto) {
        return SmartAC.builder()
                .id(dto.getId())
                .name(dto.getName())
                .unit(dto.getUnit())
                .status(dto.getStatus())
                .data(dto.getData())
                .build();
    }
}
