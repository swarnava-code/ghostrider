package com.swarnava.ghostrider.controller;

import com.swarnava.ghostrider.dto.RideRequestDTO;
import com.swarnava.ghostrider.entity.RideRequest;
import com.swarnava.ghostrider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @PostMapping("/requestride")
    public ResponseEntity requestRide(@RequestBody RideRequestDTO rideRequestDTO) {
        RideRequest rideRequest = riderService.requestRide(rideRequestDTO.userId(),
                rideRequestDTO.pickupLocation(), rideRequestDTO.destination());
        return ResponseEntity.ok().body(rideRequest);
    }

}
