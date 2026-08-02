package com.teamwill.pfa.medtech.home_manager.controller;

import com.teamwill.pfa.medtech.home_manager.dto.PreferenceRuleDto;
import com.teamwill.pfa.medtech.home_manager.service.PreferenceRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences")
@RequiredArgsConstructor
public class PreferenceRuleController {

    private final PreferenceRuleService preferenceRuleService;

    @PostMapping
    public ResponseEntity<PreferenceRuleDto> createRule(@RequestBody PreferenceRuleDto dto) {
        return ResponseEntity.ok(preferenceRuleService.createRule(dto));
    }

    @GetMapping
    public ResponseEntity<List<PreferenceRuleDto>> getRules(@RequestParam Long userId) {
        return ResponseEntity.ok(preferenceRuleService.getRulesForUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreferenceRuleDto> updateRule(@PathVariable Long id, @RequestBody PreferenceRuleDto dto) {
        return ResponseEntity.ok(preferenceRuleService.updateRule(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id) {
        preferenceRuleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }
}
