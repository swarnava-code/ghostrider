package com.swarnava.ghostrider.controller;

import com.swarnava.ghostrider.dto.RiderDTO;
import com.swarnava.ghostrider.entity.Booking;
import com.swarnava.ghostrider.entity.Rider;
import com.swarnava.ghostrider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class RiderController {

    @Autowired
    private RiderService riderService;

    /**
     * @param riderDTO
     * @return
     * @throws IOException
     * @user client app
     */
    @PatchMapping("/location")
    public ResponseEntity<Rider> updateLocation(@RequestBody RiderDTO riderDTO) throws IOException {
        return riderService.updateLocation(riderDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @param riderDTO
     * @return
     * @throws IOException
     * @user rider
     */
    @PatchMapping("/availability")
    public ResponseEntity<Rider> updateAvailability(@RequestBody RiderDTO riderDTO) throws IOException {
        return riderService.updateAvailability(riderDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @param riderDTO
     * @return
     * @throws IOException
     * @user after signup / admin
     */
    @PostMapping("/rider")
    public ResponseEntity<Rider> addNewRider(@RequestBody RiderDTO riderDTO) throws IOException {
        return riderService.saveNewRider(riderDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @user rider - once journey finishes
     * @param riderId
     * @return
     * @throws IOException
     */
    @PatchMapping("/completed/{riderId}")
    public ResponseEntity<Booking> completeJourney(@PathVariable String riderId) {
        return riderService.completeJourney(riderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
