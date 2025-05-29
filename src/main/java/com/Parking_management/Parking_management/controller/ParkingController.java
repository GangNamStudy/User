package com.Parking_management.Parking_management.controller;

import com.Parking_management.Parking_management.dto.PaymentInfoDto;
import com.Parking_management.Parking_management.dto.VehicleSearchResultDto;
import com.Parking_management.Parking_management.service.ParkingService;
import com.Parking_management.Parking_management.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;
    private final VehicleService vehicleService;

    @GetMapping("/payment-info")
    public ResponseEntity<PaymentInfoDto> getPaymentInfo(@RequestParam Long id) {
        return ResponseEntity.ok()
                .body(parkingService.calculateParkingFee(id));
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleSearchResultDto>> getPaymentInfo(@RequestParam String plate) {
        return ResponseEntity.ok()
                .body(vehicleService.searchPlate(plate));
    }

}
