package com.teamwill.pfa.medtech.home_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "preference_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreferenceRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "device_id", nullable = false)
    private Long deviceId;

    // Denormalized on purpose: devices live across three separate tables
    // (curtains/bulbs/acs), so storing the name avoids a three-way lookup
    // just to display a rule. Same rationale as Command not joining Device.
    @Column(name = "device_name", nullable = false)
    private String deviceName;

    @Column(nullable = false, length = 500)
    private String condition;

    @Column(nullable = false, length = 500)
    private String action;

    @Column(nullable = false)
    private boolean strict;

    @Column(nullable = false)
    private boolean enabled;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}
