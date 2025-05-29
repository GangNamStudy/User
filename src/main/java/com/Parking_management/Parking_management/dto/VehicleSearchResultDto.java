package com.Parking_management.Parking_management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleSearchResultDto {
    private Long id;
    private String plate;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean parked;
}
