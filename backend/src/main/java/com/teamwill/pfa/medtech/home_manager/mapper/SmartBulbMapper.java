package com.teamwill.pfa.medtech.home_manager.mapper;

import com.teamwill.pfa.medtech.home_manager.dto.SmartBulbDto;
import com.teamwill.pfa.medtech.home_manager.entity.SmartBulb;

public class SmartBulbMapper {

    public static SmartBulbDto mapToDto(SmartBulb device) {
        return SmartBulbDto.builder()
                .id(device.getId())
                .name(device.getName())
                .unit(device.getUnit())
                .status(device.getStatus())
                .data(device.getData())
                .build();
    }

    public static SmartBulb mapToEntity(SmartBulbDto dto) {
        return SmartBulb.builder()
                .id(dto.getId())
                .name(dto.getName())
                .unit(dto.getUnit())
                .status(dto.getStatus())
                .data(dto.getData())
                .build();
    }
}
