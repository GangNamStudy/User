package com.Parking_management.Parking_management.service;

import com.Parking_management.Parking_management.client.VehicleApiClient;
import com.Parking_management.Parking_management.client.VehicleApiClientFallback;
import com.Parking_management.Parking_management.dto.VehicleSearchResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleApiClient vehicleApiClient;

    public List<VehicleSearchResultDto> searchPlate(String plate) {
        try {
            List<VehicleSearchResultDto> result = vehicleApiClient.searchVehicles(plate, true, null, null, null);
            log.info("외부 차량 검색 API 호출 성공 - 검색어: {}, 결과 수: {}", plate, result.size());
            return result;
        } catch (Exception e) {
            log.warn("외부 차량 검색 API 호출 실패, Fallback 사용 - 검색어: {}, 오류: {}", plate, e.getMessage());
            VehicleApiClientFallback fallback = new VehicleApiClientFallback();
            return fallback.searchVehicles(plate, true, null, null, null);
        }
    }
}