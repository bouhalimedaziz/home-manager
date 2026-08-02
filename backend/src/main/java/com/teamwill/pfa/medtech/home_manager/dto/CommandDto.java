package com.teamwill.pfa.medtech.home_manager.dto;

import com.teamwill.pfa.medtech.home_manager.entity.CommandStatus;
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
public class CommandDto {
    private Long commandId;
    private Long deviceId;
    private String action;
    private String params;
    // Kept nullable/omittable on purpose: the ESP32 firmware's JSON parsing
    // of GET /devices/{id}/commands only ever reads "action" and
    // "commandId" (see the curtains sketch), so adding fields here is safe
    // and won't break the device-side poll loop.
    private CommandStatus status;
    private Instant createdAt;
}
