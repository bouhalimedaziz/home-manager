package com.teamwill.pfa.medtech.home_manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueueCommandRequest {
    private String action;
    private String params;
}
