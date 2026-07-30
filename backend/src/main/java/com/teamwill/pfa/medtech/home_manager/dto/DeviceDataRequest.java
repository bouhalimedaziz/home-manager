package com.teamwill.pfa.medtech.home_manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DeviceDataRequest {
    private Map<String, Object> readings;
}
