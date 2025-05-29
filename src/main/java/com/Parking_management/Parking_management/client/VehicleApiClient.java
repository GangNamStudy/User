package com.Parking_management.Parking_management.client;

import com.Parking_management.Parking_management.dto.VehicleSearchResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(
        name = "vehicle-service",
        url = "http://localhost:8082",
        fallback = VehicleApiClientFallback.class
)
public interface VehicleApiClient {

    @GetMapping("/api/vehicles/search")
    List<VehicleSearchResultDto> searchVehicles(
            @RequestParam(value = "plate", required = false) String plate,
            @RequestParam(value = "isParked", required = false) Boolean isParked,
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(value = "limit", required = false) Integer limit
    );

    @GetMapping("/api/vehicles/id/{id}")
    VehicleSearchResultDto getVehicleById(@PathVariable("id") Long id);
}
