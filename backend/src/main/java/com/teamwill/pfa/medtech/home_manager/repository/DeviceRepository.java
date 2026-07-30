package com.teamwill.pfa.medtech.home_manager.repository;

import com.teamwill.pfa.medtech.home_manager.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Generic repository over the abstract Device base entity.
 * Thanks to JOINED inheritance, this can fetch/save any concrete
 * device subtype (SmartCurtain, SmartBulb, SmartAC) by its shared id.
 */
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
