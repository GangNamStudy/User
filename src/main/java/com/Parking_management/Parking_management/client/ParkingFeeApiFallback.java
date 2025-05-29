package com.Parking_management.Parking_management.client;

import com.Parking_management.Parking_management.domain.ParkingFeePolicyDto;
import org.springframework.stereotype.Component;

@Component
public class ParkingFeeApiFallback implements ParkingFeeApiClient {

    @Override
    public ParkingFeePolicyDto getParkingFeePolicy() {
        return new ParkingFeePolicyDto(1000, 30, 500, 10);
    }
}
