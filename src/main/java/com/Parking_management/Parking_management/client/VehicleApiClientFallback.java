package com.Parking_management.Parking_management.client;

import com.Parking_management.Parking_management.dto.VehicleSearchResultDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class VehicleApiClientFallback implements VehicleApiClient {

    private final List<VehicleSearchResultDto> dummyVehicles = Arrays.asList(
            new VehicleSearchResultDto(1L, "12가1234",
                    LocalDateTime.of(2025, 2, 15, 9, 30),
                    LocalDateTime.of(2025, 2, 15, 18, 45), false),

            new VehicleSearchResultDto(2L, "23나2345",
                    LocalDateTime.of(2025, 2, 15, 10, 15),
                    null, true),

            new VehicleSearchResultDto(3L, "34다3456",
                    LocalDateTime.of(2025, 2, 14, 14, 20),
                    LocalDateTime.of(2025, 2, 14, 16, 30), false),

            new VehicleSearchResultDto(4L, "45라4567",
                    LocalDateTime.of(2025, 2, 15, 11, 45),
                    null, true),

            new VehicleSearchResultDto(5L, "56마5678",
                    LocalDateTime.of(2025, 2, 13, 8, 0),
                    LocalDateTime.of(2025, 2, 13, 17, 30), false),

            new VehicleSearchResultDto(6L, "67바6789",
                    LocalDateTime.of(2025, 2, 15, 13, 20),
                    null, true),

            new VehicleSearchResultDto(7L, "78사7890",
                    LocalDateTime.of(2025, 2, 12, 15, 10),
                    LocalDateTime.of(2025, 2, 12, 19, 20), false),

            new VehicleSearchResultDto(8L, "89아8901",
                    LocalDateTime.of(2025, 2, 15, 7, 30),
                    null, true),

            new VehicleSearchResultDto(9L, "90자9012",
                    LocalDateTime.of(2025, 2, 14, 12, 0),
                    LocalDateTime.of(2025, 2, 14, 15, 45), false),

            new VehicleSearchResultDto(10L, "01차0123",
                    LocalDateTime.of(2025, 2, 15, 16, 15),
                    null, true)
    );

    @Override
    public List<VehicleSearchResultDto> searchVehicles(String plate, Boolean isParked,
                                                       LocalDate startDate, LocalDate endDate,
                                                       Integer limit) {
        List<VehicleSearchResultDto> filteredResult = dummyVehicles.stream()
                .filter(vehicle -> {

                    if (plate != null && !vehicle.getPlate().contains(plate))
                        return false;
                    if (isParked != null && vehicle.isParked() != isParked)
                        return false;
                    if (startDate != null || endDate != null) {
                        LocalDate entryDate = vehicle.getEntryTime().toLocalDate();
                        if (startDate != null && entryDate.isBefore(startDate))
                            return false;
                        if (endDate != null && entryDate.isAfter(endDate))
                            return false;
                    }
                    return true;
                })
                .limit(limit != null ? limit : Integer.MAX_VALUE)
                .toList();
        return filteredResult;
    }

    @Override
    public VehicleSearchResultDto getVehicleById(Long id) {
        return dummyVehicles.stream()
                .filter(vehicle -> vehicle.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}