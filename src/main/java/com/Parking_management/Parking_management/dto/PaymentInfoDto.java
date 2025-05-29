package com.Parking_management.Parking_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfoDto {
    private String carNumber;
    private String entryTime;
    private String parkingDuration;
    private int amount;
}
