package com.teamwill.pfa.medtech.home_manager.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamwill.pfa.medtech.home_manager.dto.CommandDto;
import com.teamwill.pfa.medtech.home_manager.dto.DeviceDataRequest;
import com.teamwill.pfa.medtech.home_manager.dto.QueueCommandRequest;
import com.teamwill.pfa.medtech.home_manager.entity.Command;
import com.teamwill.pfa.medtech.home_manager.entity.CommandStatus;
import com.teamwill.pfa.medtech.home_manager.entity.Device;
import com.teamwill.pfa.medtech.home_manager.exception.ResourceNotFoundException;
import com.teamwill.pfa.medtech.home_manager.repository.CommandRepository;
import com.teamwill.pfa.medtech.home_manager.repository.DeviceRepository;
import com.teamwill.pfa.medtech.home_manager.service.DeviceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceDataServiceImpl implements DeviceDataService {

    private final DeviceRepository deviceRepository;
    private final CommandRepository commandRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void receiveData(Long deviceId, DeviceDataRequest request) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + deviceId));
        try {
            String json = objectMapper.writeValueAsString(request.getReadings());
            device.setData(json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize device readings", e);
        }
        deviceRepository.save(device);
    }

    @Override
    public List<CommandDto> getPendingCommands(Long deviceId) {
        return commandRepository.findByDeviceIdAndStatus(deviceId, CommandStatus.PENDING)
                .stream()
                .map(c -> CommandDto.builder()
                        .commandId(c.getId())
                        .action(c.getAction())
                        .params(c.getParams())
                        .build())
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

        return CommandDto.builder()
                .commandId(saved.getId())
                .action(saved.getAction())
                .params(saved.getParams())
                .build();
    }
}
