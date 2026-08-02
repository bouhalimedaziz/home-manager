package com.teamwill.pfa.medtech.home_manager.service.impl;

import com.teamwill.pfa.medtech.home_manager.dto.CommandDto;
import com.teamwill.pfa.medtech.home_manager.dto.QueueCommandRequest;
import com.teamwill.pfa.medtech.home_manager.dto.ReadingDto;
import com.teamwill.pfa.medtech.home_manager.entity.Command;
import com.teamwill.pfa.medtech.home_manager.entity.CommandStatus;
import com.teamwill.pfa.medtech.home_manager.entity.Device;
import com.teamwill.pfa.medtech.home_manager.entity.Reading;
import com.teamwill.pfa.medtech.home_manager.exception.ResourceNotFoundException;
import com.teamwill.pfa.medtech.home_manager.repository.CommandRepository;
import com.teamwill.pfa.medtech.home_manager.repository.DeviceRepository;
import com.teamwill.pfa.medtech.home_manager.repository.ReadingRepository;
import com.teamwill.pfa.medtech.home_manager.service.DeviceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceDataServiceImpl implements DeviceDataService {

    private final DeviceRepository deviceRepository;
    private final CommandRepository commandRepository;
    private final ReadingRepository readingRepository;

    @Override
    public void receiveData(Long deviceId, String rawReadingsJson) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + deviceId));
        device.setData(rawReadingsJson);
        deviceRepository.save(device);

        // Unlike Device.data (overwritten every time), this is an append-only
        // log — what the History sparkline on the dashboard reads from.
        Reading reading = Reading.builder()
                .deviceId(deviceId)
                .recordedAt(Instant.now())
                .data(rawReadingsJson)
                .build();
        readingRepository.save(reading);
    }

    @Override
    public List<CommandDto> getPendingCommands(Long deviceId) {
        return commandRepository.findByDeviceIdAndStatus(deviceId, CommandStatus.PENDING)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void acknowledgeCommand(Long deviceId, Long commandId, String status) {
        Command command = commandRepository.findById(commandId)
                .orElseThrow(() -> new ResourceNotFoundException("Command not found with id: " + commandId));
        command.setStatus(CommandStatus.valueOf(status));
        commandRepository.save(command);
    }

    @Override
    public CommandDto queueCommand(Long deviceId, QueueCommandRequest request) {
        deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + deviceId));

        Command command = Command.builder()
                .deviceId(deviceId)
                .action(request.getAction())
                .params(request.getParams())
                .status(CommandStatus.PENDING)
                .createdAt(Instant.now())
                .build();

        Command saved = commandRepository.save(command);
        return toDto(saved);
    }

    @Override
    public List<CommandDto> getAllCommands(int limit) {
        return commandRepository.findAll(PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "createdAt")))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReadingDto> getReadings(Long deviceId, int limit) {
        return readingRepository.findTop50ByDeviceIdOrderByRecordedAtDesc(deviceId)
                .stream()
                .limit(limit)
                .map(r -> ReadingDto.builder()
                        .id(r.getId())
                        .deviceId(r.getDeviceId())
                        .recordedAt(r.getRecordedAt())
                        .data(r.getData())
                        .build())
                .collect(Collectors.toList());
    }

    private CommandDto toDto(Command c) {
        return CommandDto.builder()
                .commandId(c.getId())
                .deviceId(c.getDeviceId())
                .action(c.getAction())
                .params(c.getParams())
                .status(c.getStatus())
                .createdAt(c.getCreatedAt())
                .build();
    }
}
