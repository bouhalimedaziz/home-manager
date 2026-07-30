package com.teamwill.pfa.medtech.home_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "commands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", nullable = false)
    private Long deviceId;

    @Column(nullable = false)
    private String action;

    @Column(length = 2000)
    private String params;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommandStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}
