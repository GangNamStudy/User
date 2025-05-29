package com.Parking_management.Parking_management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingFeePolicyDto {
    private int baseFee;
    private int freeTime;
    private int additionalFee;
    private int additionalTime;
}
