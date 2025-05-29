package com.Parking_management.Parking_management.service;

import com.Parking_management.Parking_management.client.ParkingFeeApiClient;
import com.Parking_management.Parking_management.client.ParkingFeeApiFallback;
import com.Parking_management.Parking_management.client.VehicleApiClient;
import com.Parking_management.Parking_management.client.VehicleApiClientFallback;
import com.Parking_management.Parking_management.domain.ParkingFeePolicyDto;
import com.Parking_management.Parking_management.dto.PaymentInfoDto;
import com.Parking_management.Parking_management.dto.VehicleSearchResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingService {

    private final ParkingFeeApiClient parkingFeeApiClient;
    private final VehicleApiClient vehicleApiClient;

    public PaymentInfoDto calculateParkingFee(Long id) {
        VehicleSearchResultDto vehicle = getVehicleWithFallback(id);
        ParkingFeePolicyDto policy = getParkingFeePolicyWithFallback();

        LocalDateTime exitTime = vehicle.getExitTime() != null ?
                vehicle.getExitTime() : LocalDateTime.now();

        long parkingMinutes = Duration.between(vehicle.getEntryTime(), exitTime).toMinutes();

        int amount = 0;
        if (parkingMinutes > policy.getFreeTime()) {
            amount = policy.getBaseFee();
            long additionalMinutes = parkingMinutes - policy.getFreeTime();
            long additionalBlocks = (long) Math.ceil((double) additionalMinutes / policy.getAdditionalTime());
            amount += (int) (additionalBlocks * policy.getAdditionalFee());
        }

        String duration = (parkingMinutes / 60) + "시간 " + (parkingMinutes % 60) + "분";

        return new PaymentInfoDto(
                vehicle.getPlate(),
                vehicle.getEntryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                duration,
                amount
        );
    }

    private VehicleSearchResultDto getVehicleWithFallback(Long id) {
        try {
            VehicleSearchResultDto result = vehicleApiClient.getVehicleById(id);
            log.info("차량 정보 API 호출 성공 - ID: {}", id);
            return result;
        } catch (Exception e) {
            log.warn("차량 정보 API 호출 실패, Fallback 사용 - ID: {}, 오류: {}", id, e.getMessage());
            VehicleApiClientFallback fallback = new VehicleApiClientFallback();
            return fallback.getVehicleById(id);
        }
    }

    private ParkingFeePolicyDto getParkingFeePolicyWithFallback() {
        try {
            ParkingFeePolicyDto result = parkingFeeApiClient.getParkingFeePolicy();
            log.info("주차 요금 정책 API 호출 성공");
            return result;
        } catch (Exception e) {
            log.warn("주차 요금 정책 API 호출 실패, Fallback 사용 - 오류: {}", e.getMessage());
            ParkingFeeApiFallback fallback = new ParkingFeeApiFallback();
            return fallback.getParkingFeePolicy();
        }
    }
}