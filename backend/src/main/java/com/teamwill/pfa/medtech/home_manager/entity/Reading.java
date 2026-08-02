package com.teamwill.pfa.medtech.home_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

// A point-in-time snapshot of a device's reported data. Appended (not
// overwritten) every time a device posts to /devices/{id}/data, unlike
// Device.data itself which only ever holds the latest value.
@Entity
@Table(name = "readings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", nullable = false)
    private Long deviceId;

    @Column(name = "recorded_at", nullable = false)
    private Instant recordedAt;

    @Column(length = 2000)
    private String data;
}
