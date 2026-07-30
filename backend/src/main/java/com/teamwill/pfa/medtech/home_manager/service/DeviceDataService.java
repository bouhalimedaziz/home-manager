package com.teamwill.pfa.medtech.home_manager.service;

import com.teamwill.pfa.medtech.home_manager.dto.CommandDto;
import com.teamwill.pfa.medtech.home_manager.dto.DeviceDataRequest;
import com.teamwill.pfa.medtech.home_manager.dto.QueueCommandRequest;

import java.util.List;

public interface DeviceDataService {
    void receiveData(Long deviceId, DeviceDataRequest request);
    List<CommandDto> getPendingCommands(Long deviceId);
    void acknowledgeCommand(Long deviceId, Long commandId, String status);
    CommandDto queueCommand(Long deviceId, QueueCommandRequest request);
}
