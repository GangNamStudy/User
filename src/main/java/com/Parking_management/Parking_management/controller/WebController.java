package com.Parking_management.Parking_management.controller;

import com.Parking_management.Parking_management.dto.PaymentInfoDto;
import com.Parking_management.Parking_management.service.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class WebController {

    private final ParkingService parkingService;

    @GetMapping("/")
    public String vehicleSearchPage() {
        return "index";
    }

    @GetMapping("/payment")
    public String paymentPage(@RequestParam Long id, Model model) {
        PaymentInfoDto paymentInfo = parkingService.calculateParkingFee(id);
        model.addAttribute("paymentInfo", paymentInfo);
        return "payment";
    }
}
