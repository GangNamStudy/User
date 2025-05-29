package com.Parking_management.Parking_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleExitDto {
    private String status;
    private String plate;
    private String entryTime;
}