package com.teamwill.pfa.medtech.home_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class DeviceDto {
    private Long id;
    private String name;
    private Long unit;
    private String status;
    private String data;
}
