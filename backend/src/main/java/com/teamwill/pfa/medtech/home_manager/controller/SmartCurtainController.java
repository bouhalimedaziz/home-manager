package com.teamwill.pfa.medtech.home_manager.controller;

import com.teamwill.pfa.medtech.home_manager.dto.SmartCurtainDto;
import com.teamwill.pfa.medtech.home_manager.service.SmartCurtainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices/curtains")
@RequiredArgsConstructor
public class SmartCurtainController {

    private final SmartCurtainService smartCurtainService;

    @PostMapping
    public ResponseEntity<SmartCurtainDto> createSmartCurtain(@RequestBody SmartCurtainDto dto) {
        return ResponseEntity.ok(smartCurtainService.createSmartCurtain(dto));
    }

    @GetMapping
    public ResponseEntity<List<SmartCurtainDto>> getAllSmartCurtains() {
        return ResponseEntity.ok(smartCurtainService.getAllSmartCurtains());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmartCurtainDto> getSmartCurtainById(@PathVariable Long id) {
        return ResponseEntity.ok(smartCurtainService.getSmartCurtainById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SmartCurtainDto> updateSmartCurtain(@PathVariable Long id, @RequestBody SmartCurtainDto dto) {
        return ResponseEntity.ok(smartCurtainService.updateSmartCurtain(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSmartCurtain(@PathVariable Long id) {
        smartCurtainService.deleteSmartCurtain(id);
        return ResponseEntity.noContent().build();
    }
}
