package com.teamwill.pfa.medtech.home_manager.service.impl;

import com.teamwill.pfa.medtech.home_manager.dto.SmartCurtainDto;
import com.teamwill.pfa.medtech.home_manager.entity.SmartCurtain;
import com.teamwill.pfa.medtech.home_manager.exception.ResourceNotFoundException;
import com.teamwill.pfa.medtech.home_manager.mapper.SmartCurtainMapper;
import com.teamwill.pfa.medtech.home_manager.repository.SmartCurtainRepository;
import com.teamwill.pfa.medtech.home_manager.service.SmartCurtainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SmartCurtainServiceImpl implements SmartCurtainService {

    private final SmartCurtainRepository smartCurtainRepository;

    @Override
    public SmartCurtainDto createSmartCurtain(SmartCurtainDto dto) {
        SmartCurtain saved = smartCurtainRepository.save(SmartCurtainMapper.mapToEntity(dto));
        return SmartCurtainMapper.mapToDto(saved);
    }

    @Override
    public List<SmartCurtainDto> getAllSmartCurtains() {
        return smartCurtainRepository.findAll()
                .stream()
                .map(SmartCurtainMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SmartCurtainDto getSmartCurtainById(Long id) {
        SmartCurtain device = smartCurtainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SmartCurtain not found with id: " + id));
        return SmartCurtainMapper.mapToDto(device);
    }

    @Override
    public SmartCurtainDto updateSmartCurtain(Long id, SmartCurtainDto dto) {
        SmartCurtain existing = smartCurtainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SmartCurtain not found with id: " + id));

        existing.setName(dto.getName());
        existing.setUnit(dto.getUnit());
        existing.setStatus(dto.getStatus());
        existing.setData(dto.getData());

        return SmartCurtainMapper.mapToDto(smartCurtainRepository.save(existing));
    }

    @Override
    public void deleteSmartCurtain(Long id) {
        if (!smartCurtainRepository.existsById(id)) {
            throw new ResourceNotFoundException("SmartCurtain not found with id: " + id);
        }
        smartCurtainRepository.deleteById(id);
    }
}
