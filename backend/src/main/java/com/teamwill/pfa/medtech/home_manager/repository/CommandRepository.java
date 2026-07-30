package com.teamwill.pfa.medtech.home_manager.repository;

import com.teamwill.pfa.medtech.home_manager.entity.Command;
import com.teamwill.pfa.medtech.home_manager.entity.CommandStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandRepository extends JpaRepository<Command, Long> {
    List<Command> findByDeviceIdAndStatus(Long deviceId, CommandStatus status);
}
