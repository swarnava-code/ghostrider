package com.swarnava.ghostrider.controller;

import com.swarnava.ghostrider.dto.BookingDTO;
import com.swarnava.ghostrider.entity.Booking;
import com.swarnava.ghostrider.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity bookRide(@RequestBody BookingDTO bookingDTO) throws IOException {
        Booking booking = bookingService.requestRide(bookingDTO);
        return ResponseEntity.accepted().body(booking);
    }

    @GetMapping("/active-booking/{userId}")
    public ResponseEntity<Booking> checkBooking(@PathVariable String userId) {
        return bookingService.findRecentBooking(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/last-booking/{userId}")
    public ResponseEntity<Booking> checkLastBooking(@PathVariable String userId) {
        return bookingService.findLastBooking(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/cancel")
    public ResponseEntity<Booking> cancelBooking(@RequestBody BookingDTO bookingDTO) throws IOException {
        return bookingService.cancelBooking(bookingDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
