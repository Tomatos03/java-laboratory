package com.tomatos.lab.crud.mysql.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PerformanceMetrics {
    private String operationType;
    private String indexType;
    private long elapsedTimeMs;
    private long elapsedTimeNs;
    private boolean success;
    private String errorMessage;
    private LocalDateTime timestamp;
}
