package com.Parking_management.Parking_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleSearchRequest {
    private String plate;
    private Boolean isParked;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer limit;
}
