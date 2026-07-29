package com.teamwill.pfa.medtech.home_manager.controller;

import com.teamwill.pfa.medtech.home_manager.dto.SmartBulbDto;
import com.teamwill.pfa.medtech.home_manager.service.SmartBulbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices/bulbs")
@RequiredArgsConstructor
public class SmartBulbController {

    private final SmartBulbService smartBulbService;

    @PostMapping
    public ResponseEntity<SmartBulbDto> createSmartBulb(@RequestBody SmartBulbDto dto) {
        return ResponseEntity.ok(smartBulbService.createSmartBulb(dto));
    }

    @GetMapping
    public ResponseEntity<List<SmartBulbDto>> getAllSmartBulbs() {
        return ResponseEntity.ok(smartBulbService.getAllSmartBulbs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmartBulbDto> getSmartBulbById(@PathVariable Long id) {
        return ResponseEntity.ok(smartBulbService.getSmartBulbById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SmartBulbDto> updateSmartBulb(@PathVariable Long id, @RequestBody SmartBulbDto dto) {
        return ResponseEntity.ok(smartBulbService.updateSmartBulb(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSmartBulb(@PathVariable Long id) {
        smartBulbService.deleteSmartBulb(id);
        return ResponseEntity.noContent().build();
    }
}
