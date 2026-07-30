package com.teamwill.pfa.medtech.home_manager.controller;

import com.teamwill.pfa.medtech.home_manager.dto.AckCommandRequest;
import com.teamwill.pfa.medtech.home_manager.dto.CommandDto;
import com.teamwill.pfa.medtech.home_manager.dto.QueueCommandRequest;
import com.teamwill.pfa.medtech.home_manager.service.DeviceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceDataController {

    private final DeviceDataService deviceDataService;

    @PostMapping("/{id}/data")
    public ResponseEntity<Void> receiveData(@PathVariable Long id, @RequestBody String rawBody) {
        deviceDataService.receiveData(id, rawBody);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/commands")
    public ResponseEntity<Map<String, List<CommandDto>>> getCommands(@PathVariable Long id) {
        List<CommandDto> commands = deviceDataService.getPendingCommands(id);
        return ResponseEntity.ok(Map.of("commands", commands));
    }

    @PostMapping("/{id}/commands")
    public ResponseEntity<CommandDto> queueCommand(@PathVariable Long id, @RequestBody QueueCommandRequest request) {
        return ResponseEntity.ok(deviceDataService.queueCommand(id, request));
    }

    @PostMapping("/{id}/commands/{commandId}/ack")
    public ResponseEntity<Void> acknowledgeCommand(
            @PathVariable Long id,
            @PathVariable Long commandId,
            @RequestBody AckCommandRequest request) {
        deviceDataService.acknowledgeCommand(id, commandId, request.getStatus());
        return ResponseEntity.ok().build();
    }
}
