package com.teamwill.pfa.medtech.home_manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "smart_bulbs")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class SmartBulb extends Device {
}
