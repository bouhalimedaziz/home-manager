package com.teamwill.pfa.medtech.home_manager.service.impl;

import com.teamwill.pfa.medtech.home_manager.dto.SmartACDto;
import com.teamwill.pfa.medtech.home_manager.entity.SmartAC;
import com.teamwill.pfa.medtech.home_manager.exception.ResourceNotFoundException;
import com.teamwill.pfa.medtech.home_manager.mapper.SmartACMapper;
import com.teamwill.pfa.medtech.home_manager.repository.SmartACRepository;
import com.teamwill.pfa.medtech.home_manager.service.SmartACService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SmartACServiceImpl implements SmartACService {

    private final SmartACRepository smartACRepository;

    @Override
    public SmartACDto createSmartAC(SmartACDto dto) {
        SmartAC saved = smartACRepository.save(SmartACMapper.mapToEntity(dto));
        return SmartACMapper.mapToDto(saved);
    }

    @Override
    public List<SmartACDto> getAllSmartACs() {
        return smartACRepository.findAll()
                .stream()
                .map(SmartACMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SmartACDto getSmartACById(Long id) {
        SmartAC device = smartACRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SmartAC not found with id: " + id));
        return SmartACMapper.mapToDto(device);
    }

    @Override
    public SmartACDto updateSmartAC(Long id, SmartACDto dto) {
        SmartAC existing = smartACRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SmartAC not found with id: " + id));

        existing.setName(dto.getName());
        existing.setUnit(dto.getUnit());
        existing.setStatus(dto.getStatus());
        existing.setData(dto.getData());

        return SmartACMapper.mapToDto(smartACRepository.save(existing));
    }

    @Override
    public void deleteSmartAC(Long id) {
        if (!smartACRepository.existsById(id)) {
            throw new ResourceNotFoundException("SmartAC not found with id: " + id);
        }
        smartACRepository.deleteById(id);
    }
}
