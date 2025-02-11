package com.swarnava.ghostrider.dto;

import com.swarnava.ghostrider.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO implements Serializable {
    private String userId;
    private Location pickupLocation; // Location
    private Location destination;
}
