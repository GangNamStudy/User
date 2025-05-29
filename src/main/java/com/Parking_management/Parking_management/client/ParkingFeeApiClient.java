package com.Parking_management.Parking_management.client;

import com.Parking_management.Parking_management.domain.ParkingFeePolicyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "parking-fee-service",
        url = "http://localhost:8080",
        fallback = ParkingFeeApiFallback.class)
public interface ParkingFeeApiClient {

    @GetMapping("/api/parking-fees/policy")
    ParkingFeePolicyDto getParkingFeePolicy();
}
