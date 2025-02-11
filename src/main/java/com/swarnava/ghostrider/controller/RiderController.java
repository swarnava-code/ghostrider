package com.swarnava.ghostrider.controller;

import com.swarnava.ghostrider.dto.RideRequestDTO;
import com.swarnava.ghostrider.entity.RideRequest;
import com.swarnava.ghostrider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @PostMapping("/requestride")
    public ResponseEntity requestRide(@RequestBody RideRequestDTO rideRequestDTO) throws IOException {
        RideRequest rideRequest = riderService.requestRide(rideRequestDTO);
        return ResponseEntity.accepted().body(rideRequest);
    }

    @GetMapping("/recentbooking/{userId}")
    public ResponseEntity checkRide(@PathVariable String userId) {
        Optional<RideRequest> rideRequest = riderService.findRecentBooking(userId);
        return ResponseEntity.accepted().body(rideRequest.get());
    }

}
