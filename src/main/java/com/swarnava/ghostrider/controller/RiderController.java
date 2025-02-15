package com.swarnava.ghostrider.controller;

import com.swarnava.ghostrider.dto.CreateRiderDTO;
import com.swarnava.ghostrider.dto.ModifiableRiderDTO;
import com.swarnava.ghostrider.dto.RiderDTO;
import com.swarnava.ghostrider.entity.Booking;
import com.swarnava.ghostrider.entity.Rider;
import com.swarnava.ghostrider.service.RiderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @GetMapping("/rider/{riderId}")
    public ResponseEntity<Rider> findRider(@PathVariable String riderId) throws IOException {
        return riderService.findRider(riderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @param riderDTO
     * @return
     * @throws IOException
     * @user client app
     */
    @PatchMapping("/location/{riderId}")
    public ResponseEntity<Rider> updateLocation(@PathVariable String riderId,
                                                @RequestBody RiderDTO riderDTO) throws IOException {
        return riderService.updateLocation(riderId, riderDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @param riderDTO
     * @return
     * @throws IOException
     * @user rider
     */
    @PatchMapping("/availability/{riderId}")
    public ResponseEntity<Rider> updateAvailability(@PathVariable String riderId,
                                                    @RequestBody RiderDTO riderDTO) throws IOException {
        return riderService.updateAvailability(riderId, riderDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/update/{riderId}")
    public ResponseEntity<Rider> update(@PathVariable String riderId,
                                        @RequestBody ModifiableRiderDTO modifiableRiderDTO) throws IOException {
        return riderService.update(riderId, modifiableRiderDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @param createRiderDTO
     * @return
     * @throws IOException
     * @user when signup / admin
     */
    @PostMapping("/rider")
    public ResponseEntity<Rider> addNewRider(@Valid @RequestBody CreateRiderDTO createRiderDTO) throws IOException {
        return riderService.saveNewRider(createRiderDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @param riderId
     * @return
     * @throws IOException
     * @user rider - once journey finishes
     */
    @PatchMapping("/completed/{riderId}")
    public ResponseEntity<Booking> completeJourney(@PathVariable String riderId) {
        return riderService.completeJourney(riderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
