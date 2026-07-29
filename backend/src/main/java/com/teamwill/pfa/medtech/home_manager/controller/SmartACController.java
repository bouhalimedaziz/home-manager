package com.teamwill.pfa.medtech.home_manager.controller;

import com.teamwill.pfa.medtech.home_manager.dto.SmartACDto;
import com.teamwill.pfa.medtech.home_manager.service.SmartACService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices/ac")
@RequiredArgsConstructor
public class SmartACController {

    private final SmartACService smartACService;

    @PostMapping
    public ResponseEntity<SmartACDto> createSmartAC(@RequestBody SmartACDto dto) {
        return ResponseEntity.ok(smartACService.createSmartAC(dto));
    }

    @GetMapping
    public ResponseEntity<List<SmartACDto>> getAllSmartACs() {
        return ResponseEntity.ok(smartACService.getAllSmartACs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmartACDto> getSmartACById(@PathVariable Long id) {
        return ResponseEntity.ok(smartACService.getSmartACById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SmartACDto> updateSmartAC(@PathVariable Long id, @RequestBody SmartACDto dto) {
        return ResponseEntity.ok(smartACService.updateSmartAC(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSmartAC(@PathVariable Long id) {
        smartACService.deleteSmartAC(id);
        return ResponseEntity.noContent().build();
    }
}
