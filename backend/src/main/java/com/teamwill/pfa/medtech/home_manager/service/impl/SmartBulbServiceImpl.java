package com.teamwill.pfa.medtech.home_manager.service.impl;

import com.teamwill.pfa.medtech.home_manager.dto.SmartBulbDto;
import com.teamwill.pfa.medtech.home_manager.entity.SmartBulb;
import com.teamwill.pfa.medtech.home_manager.exception.ResourceNotFoundException;
import com.teamwill.pfa.medtech.home_manager.mapper.SmartBulbMapper;
import com.teamwill.pfa.medtech.home_manager.repository.SmartBulbRepository;
import com.teamwill.pfa.medtech.home_manager.service.SmartBulbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SmartBulbServiceImpl implements SmartBulbService {

    private final SmartBulbRepository smartBulbRepository;

    @Override
    public SmartBulbDto createSmartBulb(SmartBulbDto dto) {
        SmartBulb saved = smartBulbRepository.save(SmartBulbMapper.mapToEntity(dto));
        return SmartBulbMapper.mapToDto(saved);
    }

    @Override
    public List<SmartBulbDto> getAllSmartBulbs() {
        return smartBulbRepository.findAll()
                .stream()
                .map(SmartBulbMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SmartBulbDto getSmartBulbById(Long id) {
        SmartBulb device = smartBulbRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SmartBulb not found with id: " + id));
        return SmartBulbMapper.mapToDto(device);
    }

    @Override
    public SmartBulbDto updateSmartBulb(Long id, SmartBulbDto dto) {
        SmartBulb existing = smartBulbRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SmartBulb not found with id: " + id));

        existing.setName(dto.getName());
        existing.setUnit(dto.getUnit());
        existing.setStatus(dto.getStatus());
        existing.setData(dto.getData());

        return SmartBulbMapper.mapToDto(smartBulbRepository.save(existing));
    }

    @Override
    public void deleteSmartBulb(Long id) {
        if (!smartBulbRepository.existsById(id)) {
            throw new ResourceNotFoundException("SmartBulb not found with id: " + id);
        }
        smartBulbRepository.deleteById(id);
    }
}
